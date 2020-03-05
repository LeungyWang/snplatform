package entity;

import lombok.Data;


@Data
public class Result {
    private int code;
    private String msg;
    private int count;
    private Object data;

    public Result(int code, String msg, int count, Object data){
        super();
        this.code=code;
        this.msg=msg;
        this.count=count;
        this.data=data;
    }

    public Result(int code, String msg, int count){
        super();
        this.code=code;
        this.msg=msg;
        this.count=count;
    }



}
