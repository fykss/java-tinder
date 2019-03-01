package service;

import dao.daoLiked.DaoLiked;
import dao.daoLiked.DbDaoLikes;
import dto.Like;

import java.sql.Connection;
import java.util.Date;

public class ServiceLikes {

    private DaoLiked daoLikes;

    public ServiceLikes(Connection dbConn) {
        this.daoLikes = new DbDaoLikes(dbConn);
    }

    public void saveLike(Like like){
        daoLikes.save(like);
    }

    public void updateLike(Like like){
        daoLikes.update(like);
    }

    public Like createLike(Date date, int... var){
        Like like = new Like();
        like.setUserIdWho(var[0]);
        like.setUserIdWhom(var[1]);
        like.setDate(date);
        return like;
    }

    public boolean checkLike(Like like){
        return daoLikes.check(like);
    }

}
