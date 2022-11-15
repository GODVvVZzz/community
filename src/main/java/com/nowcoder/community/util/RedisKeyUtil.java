package com.nowcoder.community.util;

/**
 * @author VvV
 * @date 2022/9/16
 * 点赞 like
 */
public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_USER_LIKE = "like:user";
    //关注目标
    private static final String PREFIX_FOLLOWEE = "followee";
    //粉丝
    private static final String PREFIX_FOLLOWER = "follower";
    private static final String PREFIX_KAPTCHA = "kaptcha";  //验证码
    private static final String PREFIX_TICKET = "ticket";  //登录凭证
    private static final String PREFIX_USER = "user";   //登录用户

    //数据统计
    private static final String PREFIX_UV = "uv";
    private static final String PREFIX_DAU = "dau";
    //帖子分数
    private static final String PREFIX_POST = "post";
    //热帖
    private static final String PREFIX_HOT_POST = "hot:post";

    /**
     * 某个实体的赞
     * like:entity:entityType:entityId -> set(userId1, userId2, userId3,...)
     * 赞：实体：类型（帖子/评论）；实体的id
     */
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    /**
     * 用户收到的赞
     */
    public static String getUserLikeKey(int userId){
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    // 某个用户关注的实体
    // followee:userId:entityType -> zset(entityId,now)
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    // 某个实体拥有的粉丝
    // follower:entityType:entityId -> zset(userId,now)
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    // 登录验证码
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    // 登录的凭证
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }

    // 用户
    public static String getUserKey(int userId) {
        return PREFIX_USER + SPLIT + userId;
    }

    // 单日UV
    public static String getUVKey(String date) {
        return PREFIX_UV + SPLIT + date;
    }

    // 区间UV
    public static String getUVKey(String startDate, String endDate) {
        return PREFIX_UV + SPLIT + startDate + SPLIT + endDate;
    }

    // 单日活跃用户
    public static String getDAUKey(String date) {
        return PREFIX_DAU + SPLIT + date;
    }

    // 区间活跃用户
    public static String getDAUKey(String startDate, String endDate) {
        return PREFIX_DAU + SPLIT + startDate + SPLIT + endDate;
    }

    // 统计帖子分数的key
    public static String getPostScoreKey() {
        return PREFIX_POST + SPLIT + "score";
    }

    // 热帖的key
    public static String getHotPostKey() {
        return PREFIX_HOT_POST;
    }
}
