package wang.ismy.travel.entity;

import lombok.Data;

/**
 * @author MY
 */
@Data
public class Result {

    private Boolean success;

    private String msg;

    private Object data;

    public static Result success(Object data){
        Result result = new Result();
        result.setSuccess(true);
        result.data = data;
        return result;
    }

    public static Result error(String msg){
        Result result = new Result();
        result.setSuccess(false);
        result.msg = msg;
        return result;
    }
}
