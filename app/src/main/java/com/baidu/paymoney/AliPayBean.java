package com.baidu.paymoney;

/**
 * @author qwh
 *         Created by asus on 2019/6/4.
 */

public class AliPayBean {

    /**
     * code : 0
     * desc :
     * result : {"sign":"app_id=2017071807800448&method=alipay.trade.app.pay&version=1.0&format=JSON&charset=utf8&sign_type=RSA2&timestamp=2019-06-04%2010%3A53%3A22&notify_url=https%3A%2F%2Fapi.banmi.com%2Fapi%2Fapp%2Fv3%2Fpayments%2Falibaba&biz_content=%7B%22subject%22%3A%22%E4%BC%B4%E7%B1%B3%E6%97%85%E8%A1%8C%22%2C%22out_trade_no%22%3A%22APP1559616802973ALIPAY321072%22%2C%22total_amount%22%3A%221.00%22%2C%22timeout_express%22%3A%2215m%22%7D&sign=Li%2FBFrL1OH3txIRs3yGNsY%2FWrKv9vB%2BF3gg4rakf5RuGZpqRuksr1Mf7AOySylLE809n4%2BjI3Rt3dbwxzg0AtAjuGP3%2BbQ4FAqj%2B6vaIbRIdiZhKoYTimVsdAFgpt93i3mdfl7B6x7jPfBLPyMmRAmJxwYcdZRreqT0oJiihs7LsfIrwDqjlQ9pJllmOE4AiZNuGEgBgPFNlqOiRQCplcfYNTCcw5bJGHJSiKAGOr5EpClQTJFzk28L%2BgOLZkKtXHgu62aQ1ZYxmeio8nXHfiaa0vv9D0FlTKWkFQErHcwub%2Bys5JkCwnkc%2FNiyLSlN%2BtDaMI6H9%2FRI6k6QEGyedsQ%3D%3D","id":10572}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * sign : app_id=2017071807800448&method=alipay.trade.app.pay&version=1.0&format=JSON&charset=utf8&sign_type=RSA2&timestamp=2019-06-04%2010%3A53%3A22&notify_url=https%3A%2F%2Fapi.banmi.com%2Fapi%2Fapp%2Fv3%2Fpayments%2Falibaba&biz_content=%7B%22subject%22%3A%22%E4%BC%B4%E7%B1%B3%E6%97%85%E8%A1%8C%22%2C%22out_trade_no%22%3A%22APP1559616802973ALIPAY321072%22%2C%22total_amount%22%3A%221.00%22%2C%22timeout_express%22%3A%2215m%22%7D&sign=Li%2FBFrL1OH3txIRs3yGNsY%2FWrKv9vB%2BF3gg4rakf5RuGZpqRuksr1Mf7AOySylLE809n4%2BjI3Rt3dbwxzg0AtAjuGP3%2BbQ4FAqj%2B6vaIbRIdiZhKoYTimVsdAFgpt93i3mdfl7B6x7jPfBLPyMmRAmJxwYcdZRreqT0oJiihs7LsfIrwDqjlQ9pJllmOE4AiZNuGEgBgPFNlqOiRQCplcfYNTCcw5bJGHJSiKAGOr5EpClQTJFzk28L%2BgOLZkKtXHgu62aQ1ZYxmeio8nXHfiaa0vv9D0FlTKWkFQErHcwub%2Bys5JkCwnkc%2FNiyLSlN%2BtDaMI6H9%2FRI6k6QEGyedsQ%3D%3D
         * id : 10572
         */

        private String sign;
        private int id;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
