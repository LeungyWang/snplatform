package com.wly.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wly.config.AlipayConfig;
import com.wly.entity.Order;
import com.wly.feign.ProductFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/alipay")
public class AliPayController {
        @Autowired
        private ProductFeign productFeign;

        /**
         * 对应官方例子   alipay.trade.page.pay.jsp
         *
         * @Description: 前往支付宝第三方网关进行支付
         * Copyright: Copyright (c) 2019
         * @Classname AlipayController
         * @Description notify_url 和 return_url 需要外网可以访问，建议内网穿透
         */
        @PostMapping("/goAlipay")
        @ResponseBody
        public String goAlipay(@RequestParam String orderId, HttpServletRequest request, HttpServletRequest response) throws Exception {

            Order order = productFeign.findOrderById(orderId);
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(
                    AlipayConfig.gatewayUrl,
                    AlipayConfig.app_id,
                    AlipayConfig.merchant_private_key,
                    "json",
                    AlipayConfig.charset,
                    AlipayConfig.alipay_public_key,
                    AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = orderId;
            //付款金额，必填
            String total_amount = order.getOrder_money()+"";

            //订单名称，必填
            String subject = order.getOrder_id();
            //商品描述，可空
            String body = "用户订购商品个数：";

            // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
            String timeout_express = "10m";

            //例子去官方api找
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"timeout_express\":\"" + timeout_express + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();

            return result;
        }

    @RequestMapping(value = "/return_url")
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {


        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
        if (signVerified) {//验证成功
            //商户订单号
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            String out_trade_no = (String) params.get("out_trade_no");

            //支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            String trade_no = (String) params.get("trade_no");

            //交易状态
//            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            String trade_status = (String) params.get("trade_status");

            //付款金额
//            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            String total_amount = (String) params.get("total_amount");

            Double money = Double.valueOf(total_amount);

            productFeign.pay_success(money,out_trade_no);


        } else {//验证失败
            System.out.println("fail");
        }

        return "pay2_success";
    }

}
