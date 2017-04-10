package com.vr_mu.vrmu.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**反馈模型
 * Created by zjl on 17/4/10.
 */

public class FindBackGson {

    /**
     * code : 0
     * msg : success
     * data : {"classList":[{"class_id":"0","class_name":"全部"},{"class_id":"1","class_name":"充值"},{"class_id":"2","class_name":"积分"},{"class_id":"3","class_name":"音乐"},{"class_id":"4","class_name":"MV"},{"class_id":"5","class_name":"直播"},{"class_id":"6","class_name":"哇妙"},{"class_id":"7","class_name":"社区"}],"feedbackList":[{"question":"为什么我充值不到账","content":"亲，可能是网络延迟，请稍后查看,如果你不确定,可以再尝试充多几百块到我们平台","user_icon":"http://api.vr-mu.com/Uploads/usericon/img/58622163b03b1.png","class_name":"充值"},{"question":"为什么我的积分不到账","content":"亲，可能是网络延迟，请稍后查看,如果还有疑问,可能是因为你没有往我们平台充值金钱.","user_icon":"http://api.vr-mu.com/Uploads/usericon/img/58622163b03b1.png","class_name":"积分"},{"question":"为什么音乐列表没有张学友","content":"亲，因版权问题无法提供，非常抱歉.也有可能不是没有,是因为你没有充钱在我们平台. ","user_icon":"http://api.vr-mu.com/Uploads/usericon/img/58730f5428969.png","class_name":"音乐"},{"question":"为什么播放MV没有声音","content":"亲，建议您换一台设备播放,因为你没有买我们平台推出的最新款手机啊","user_icon":"http://api.vr-mu.com/Uploads/usericon/img/58730f5428969.png","class_name":"MV"},{"question":"为什么看直播那么卡","content":"亲，网络问题请联系当地营运商,多往平台充钱,买多点流量,就会快了","user_icon":"http://api.vr-mu.com/Uploads/usericon/img/58730f5428969.png","class_name":"直播"},{"question":"哇秒平台好漂亮","content":"亲，您的支持是我们最大的前进动力,漂亮就往我们平台充多点钱,我也不介意往我支付宝充的.","user_icon":"http://api.vr-mu.com/Uploads/usericon/img/58730f5428969.png","class_name":"哇妙"}]}
     * location :
     */

    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public DataBean data;
    @SerializedName("location")
    public String location;

    public static class DataBean {
        @SerializedName("classList")
        public List<ClassListBean> classList;

        @SerializedName("feedbackList")
        public List<FeedbackListBean> feedbackList;

        public static class ClassListBean {
            /**
             * class_id : 0
             * class_name : 全部
             */

            @SerializedName("class_id")
            public String classId;

            @SerializedName("class_name")
            public String className;
        }

        public static class FeedbackListBean {
            /**
             * question : 为什么我充值不到账
             * content : 亲，可能是网络延迟，请稍后查看,如果你不确定,可以再尝试充多几百块到我们平台
             * user_icon : http://api.vr-mu.com/Uploads/usericon/img/58622163b03b1.png
             * class_name : 充值
             */

            @SerializedName("question")
            public String question;

            @SerializedName("content")
            public String content;

            @SerializedName("user_icon")
            public String userIcon;

            @SerializedName("class_name")
            public String className;
        }
    }
}
