package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author VvV
 * @date 2022/7/20
 */
@Mapper
public interface CommentMapper {
    /**
     * 根据评论类型以及该类型下哪个目标查询评论 并分页
     * @param entityType 1 评论 2 回复
     * @param entityId 查询该目标的评论
     * @param offset
     * @param limit
     * @return
     */
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    /**
     * 查询某个帖子/评论的评论总数
     * @param entityType
     * @param entityId
     * @return
     */
    int selectCommentByEntity(int entityType, int entityId);

    int insertComment(Comment comment);

    int selectPostCommentCountByUserId(int userId, int entityType);

    Comment selectCommentById(int id);

}
