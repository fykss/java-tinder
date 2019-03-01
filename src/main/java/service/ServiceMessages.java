package service;

import dao.daoLiked.DbDaoLikes;
import dao.daoMessage.DaoMessage;

import java.sql.Connection;

public class ServiceMessages {

    private DaoMessage daoMessages;

    public ServiceMessages(Connection dbConn) {
        this.dbDaoLikes = new DbDaoLikes(dbConn);
    }
}
