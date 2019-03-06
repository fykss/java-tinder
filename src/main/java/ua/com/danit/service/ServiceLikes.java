package ua.com.danit.service;

import ua.com.danit.dao.daoLiked.DaoLiked;
import ua.com.danit.dao.daoLiked.DbDaoLikes;
import ua.com.danit.dto.Like;
import java.sql.Connection;

public class ServiceLikes {

    private DaoLiked<Like> daoLiked;

    public ServiceLikes(Connection dbConn) {
        this.daoLiked = new DbDaoLikes(dbConn);
    }

    public void saveLike(int userIdWho, int userIdWhom){
        daoLiked.save(new Like(userIdWho, userIdWhom));
    }

    public void delLike(int userIdWho, int userIdWhom){
        daoLiked.del(new Like(userIdWho, userIdWhom));
    }

    public boolean checkLike(int idWho, int idWhom){
        return daoLiked.check(new Like(idWho, idWhom));
    }

}
