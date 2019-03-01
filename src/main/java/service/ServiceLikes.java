package service;

import dao.daoLiked.DbDaoLikes;
import dto.Like;

import java.sql.Connection;
import java.util.Date;

public class ServiceLikes {

    private DbDaoLikes dbDaoLikes;

    public ServiceLikes(Connection dbConn) {
        this.dbDaoLikes = new DbDaoLikes(dbConn);
    }

    public void saveLike(Like like){
        dbDaoLikes.save(like);
    }

    public void updateLike(Like like){
        dbDaoLikes.update(like);
    }

    public Like createLike(Date date, int... var){
        Like like = new Like();
        like.setUserIdWho(var[0]);
        like.setUserIdWhom(var[1]);
        like.setDate(date);
        return like;
    }

    public boolean checkLike(Like like){
        return dbDaoLikes.check(like);
    }

}
