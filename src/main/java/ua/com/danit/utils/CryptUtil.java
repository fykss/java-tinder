package ua.com.danit.utils;

import ua.com.danit.dto.Message;
import ua.com.danit.dto.User;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Stream;

public class CryptUtil{

    private static String encrypt(String string){
        byte[] bytesString = new byte[0];
        try {
            bytesString = string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bytesString);
    }

    private static String decrypt(String string){
        byte[] decode = Base64.getDecoder().decode(string);
        return new String(decode);
    }

    public static String encryptExtra(String string){
        return encrypt(string) + "@" + encrypt(Long.toString(System.currentTimeMillis()));
    }

    public static String decryptExtra(String string){
        String[] arrString = string.split("@");
        return decrypt(arrString[0]);
    }

    public static String decryptPrmName(HttpServletRequest req){
        Map<String, String[]> pm = req.getParameterMap();
        Set<String> strings = Stream.of(pm).map(Map::keySet).findFirst().get();
        return strings.stream().map(CryptUtil::decryptExtra).findFirst().get();
    }

    public static int decryptPrmValue(HttpServletRequest req){
        Map<String, String[]> pm = req.getParameterMap();
        Collection<String[]> strings = Stream.of(pm).map(Map::values).findFirst().get();
        return Integer.parseInt(strings.stream().map(s -> CryptUtil.decryptExtra(s[0])).findFirst().get());
    }

    public static Collection<IdUserForCrypt> encryptListIdUser(Collection<User> allLikedUser){
        ArrayList<IdUserForCrypt> rewrittenLikedUser = new ArrayList<>();
        allLikedUser.forEach(user -> {
            String id = CryptUtil.encryptExtra(Integer.toString(user.getId()));
            String name = user.getName();
            String surname = user.getSurname();
            String position = user.getPosition();
            String urlImg = user.getUrlImg();
            Date date = user.getDate();
            IdUserForCrypt rewrittenUser = new IdUserForCrypt(id, name, surname, position, urlImg, date);
            rewrittenLikedUser.add(rewrittenUser);
        });
        return rewrittenLikedUser;
    }

    public static Collection<Message> decryptListMessagesUser(Collection<Message> allMessages){
        ArrayList<Message> rewrittenListMessages = new ArrayList<>();
        allMessages.forEach(message -> {
            int id = message.getId();
            int sender = message.getSender();
            int recipient = message.getRecipient();
            String textMessage = CryptUtil.decryptExtra(message.getTextMessage());
            Date date = message.getDate();
            Message rewrittenMessage = new Message(id, sender, recipient, textMessage, date);
            rewrittenListMessages.add(rewrittenMessage);
        });
        return rewrittenListMessages;
    }
}
