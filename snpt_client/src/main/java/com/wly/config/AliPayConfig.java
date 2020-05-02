package com.wly.config;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102200738406";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDXC0xVCRmnxx8WsXjcwuv1IONh99dDTi0TWW6wF9EfpM/5fvyWMOPFJH+6eDsrMD8FFqcEYyKBOG3GPhvWKKY10E11AcNh828oN4ktABq+SJX8ZTi1SwQV41zKo+0/7d3e6LriIjocSLWRgeGj2lCNillzbXoubxsDz4JHrA22KL2hS6UnfgYI1zPS39+0GEZBwHXsWkfBC3ZleU61bhzLJy0cBHa51W2Nq0nrGr+Txfzk/S3VXp1AWpOUVAgmVmpTTsfPuW1MUlboqRBvk5q6/uBb4frYiZ5TnxrR3QR60vL59sOsp0AEXDi3g8448XWxFtx7APNBZiJvpgfJc1JbAgMBAAECggEBAKnhfgrLZp4ZeuDVkNJtljQPKDJTtZWxI4DerMel+QnTjIzgGbCYrMcn/1OS+cMzmQVvEMAxhKeqqWXr2NfFQxDOHvKpUu2FfzG5Nb/8DiR2eS5r1HG78l1MQRjAiYNwaU9YcZKFUXzYn790aImlxO8b9XBvcw9NrHzFKHlwXWChufTyqz2SiESfunJOkprAL1FRYJFnu9InxBs1VkCusb/DoDNqs+oHXUVgVSdTRh8PaUqSraDxXz+IxhQsbE7Yu1CV/lWoNANGc4yC+tFYrpdaaQBTnS4+kD5RQ1EjMaAk0Dfm0gpQ22JU/h89VCxTw0ao0Lm4Gft6CQJd4IiGUikCgYEA7fRv9B/5l/6nSTqwFTQd2yyQNgL3WXDLlSLyxqGg/f3uuFQOKx64YZVnqvVvBCV+yQfrsZErkTnpHF+5yMfBW91T+Rlx63D7R1Cl01U+fhO2TtO8oCl46rs0icbvrNquNa5O+hNVNfCZ7R+PVnvpr+TXcK2Yw4xLh2CqFPZEbo0CgYEA51oU2I+NEGMaI8Y9vYdJlkHPhI32jXzWbH8qJzEIy5m5l1WNj5ERyUmPsSNO1aaSCxM6MuJxhFMGqvTiFMol8voTKQr4BgAiznzUSFSgqCMjKlF4QQHFW3jhtH+2awvpBqGX7ODex+CKUIRuYt+l9FCs3AJKfsFAvI07WrwqnocCgYAoQGcHtrjz2yHVI1ZR3+UFBD7IxCuvN4NYkPbcXs7q6B+Pkrhq1uF9sFY+9xF2dBlETFC0Usg54r/qCs9UhWXR8liYFJPOrefzolLKsurFTPRT1UFdHmbT3iL/A2XbqYo6jmfRqZ+KZk7Da8lFaeSX2nAPIW5r9bl5BwL+VMIyqQKBgDNXemjU8qlAVPy7cdgtvdbqo/L2zlB3894AGoq4BwuoAdfj1JaXyyqweIMHIuenH6H1kmTYe/ixtDkcP0/9zI4vkTD6jjBGt3K7oSCsJzspERTDH7zNd87k9LHp5cG70ur10ZFwFgClhAx6V0T5/QpLHRF2MLIQkR1G+nUYbRzhAoGBANajDPzBiCOYl2wU/zGV2OLijZM4bBQEMIlQ3oKYZvHFoDV/13qKtKc+ovxDtQ4n1V54KTXmUAXuNv8TuWnI3BocjgrcZ7A8l0JH2P0elUj4IWraohfo8ru0nYL5uRPyzu/OyEJjRnOzKihVYS1l1Mmu7PKoEMGrq5cmtvrsReXo";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmvLAJ9egnqys0+PAzMW8vfrQ8+bRONPrbyvFPPw/f+9xPq/eO30RFl3WYZRHLNl9YPAH8nlAjd4cv13SovuOwhZCApOYtYJK/xzjUbNhfcWvwWhwZBSMJ6OJ14RhwolyfulaAX5MderOpskjTBgn2wnihAM6/vQjhezTI46mgWdGFpkMfI7hgE7IOuA/qcdMZxkvJ8WuLK06PQA0gmJkflNw0K5HbOldTcNlzqpoXdeBUyOuvxRBpC0tLXXPk/MDQxoXpglDfsNHY0I6JBz1QuuDre5oFbfMAIC851trsffoJoxyhxRWMakLWeDmw3QwWuAH8ylVu2NIq2b6/VZgoQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问，测试修改springboot端口和外网地址
    public static String notify_url = "http://jfbrk4.natappfree.cc/alipay/notify_url";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网（通过netapp获取net123或自己购买生成）可以正常访问
    public static String return_url = "http://jfbrk4.natappfree.cc/alipay/return_url";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝网关
    public static String log_path = "./";
    /** * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）*/
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

