package ua.com.danit.service;

import ua.com.danit.dao.daoLiked.DbDaoLikes;
import ua.com.danit.dto.Like;

import java.sql.Connection;
import java.util.Collection;
import java.util.Date;

public class ServiceLikes {

    private DbDaoLikes dbDaoLikes;

    public ServiceLikes(Connection dbConn) {
        this.dbDaoLikes = new DbDaoLikes(dbConn);
    }

    public void saveLike(Like like){
        dbDaoLikes.save(like);
    }

    public void delLike(int idWho,int idWhom){
        Like like = new Like(idWho, idWhom);
        dbDaoLikes.del(like);
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

    public boolean checkLike(int idWho, int idWhom){
        Like like = new Like(idWho, idWhom);
        return dbDaoLikes.check(like);
    }



    public Collection<Like> getAllLike(int idUser){
        return dbDaoLikes.getAll(idUser);
    }
}
