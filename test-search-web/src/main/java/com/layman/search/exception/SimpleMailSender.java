//package com.layman.search.exception;
//
//import jdk.nashorn.internal.objects.Global;
//import org.apache.zookeeper.server.SessionTracker;
//
//import java.util.Properties;
//
//public class SimpleMailSender {
//    private final transient Properties props = System.getProperties();
//
//    private transient MailAuthenticator authenticator;
//
//    private transient SessionTracker.Session session;
//    // 从本地缓存中获取邮件相关配置信息
//    private static final String USERNAME = Global.getSysValue("mail_sender_username");
//    private static final String PASSWORD = Global.getSysValue("mail_sender_password");
//    private static final String MAIL_SMTP_HOST = Global.getSysValue("mail_smtp_host");
//    private static final String MAIL_SMTP_PORT = Global.getSysValue("mail_smtp_port");
//    private static final String MAIL_SMTP_AUTH = Global.getSysValue("mail_smtp_auth").equals("1") ? "true" : "false";
//
//    public SimpleMailSender() {
//        init();
//    }
//
//    /**
//     * 初始化
//     *
//     * 创建时间：2017年2月28日
//     *
//     * @author HY
//     * @param smtpHostName
//     *            SMTP服务器地址
//     * @param username
//     *            发送邮件的用户名（邮箱地址）
//     * @param password
//     *            密码
//     */
//    private void init() {
//        // 初始化Properties
//        props.put("mail.smtp.host", MAIL_SMTP_HOST);
//        props.put("mail.smtp.port", MAIL_SMTP_PORT);
//        props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
//        // 验证发送者账户密码
//        authenticator = new MailAuthenticator(USERNAME, PASSWORD);
//        // 创建Session
//        session = Session.getInstance(props, authenticator);
//    }
//
//    /**
//     * 发送邮件（单发）
//     *
//     * 创建时间：2017年2月28日
//     *
//     * @author HY
//     * @param recipient
//     *            收件人邮箱地址
//     * @param subject
//     *            邮件主题
//     * @param content
//     *            邮箱内容
//     * @throws AddressException
//     * @throws MessagingException
//     */
//    public void send(String recipient, String subject, Object content) throws AddressException, MessagingException {
//        // 1.创建mime类型邮件
//        final Message message = new MimeMessage(session);
//        // 2.设置发件人
//        message.setFrom(new InternetAddress(authenticator.getUsername()));
//        // 3.设置收件人
//        message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
//        // 4.设置邮件主题
//        message.setSubject(subject);
//        // 5.设置邮件内容
//        message.setContent(content.toString(), "text/html;charset=utf-8");
//        // 6.发送
//        Transport.send(message);
//    }
//
//    /**
//     * 群发邮件
//     *
//     * 创建时间：2017年2月28日
//     *
//     * @author HY
//     * @param recipients
//     *            多个收件人（邮箱地址集合）
//     * @param subject
//     *            邮件主题
//     * @param content
//     *            邮件内容
//     * @throws AddressException
//     * @throws MessagingException
//     */
//    public void send(List<String> recipients, String subject, Object content) throws AddressException, MessagingException {
//        // 1.创建mime类型邮件
//        final Message message = new MimeMessage(session);
//        // 2.设置发件人
//        message.setFrom(new InternetAddress(authenticator.getUsername()));
//        // 3.设置收件人
//        final int num = recipients.size();
//        InternetAddress[] addresses = new InternetAddress[num];
//        for (int i = 0; i < num; i++) {
//            addresses[i] = new InternetAddress(recipients.get(i));
//        }
//        message.setRecipients(RecipientType.TO, addresses);
//        // 4.设置邮件主题
//        message.setSubject(subject);
//        // 5.设置邮件内容
//        message.setContent(content.toString(), "text/html;charset=utf-8");
//        // 6.发送
//        Transport.send(message);
//    }
//}
