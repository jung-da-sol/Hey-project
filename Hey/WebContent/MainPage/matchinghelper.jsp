<%@page import="java.net.InetAddress"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="javax.mail.internet.AddressException"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Session"%>
<%@ page import="java.util.*, javax.mail.Authenticator, javax.mail.PasswordAuthentication" %>
<%@page import="dao.MemberDAO"%>
<%@page import="vo.SuccessVO"%>
<%@page import="dao.SuccessDAO"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	SuccessDAO sdao = new SuccessDAO();
	SuccessVO svo = new SuccessVO();
	svo.setBoard_seq(Integer.parseInt(request.getParameter("seq")));
	svo.setHelper_id(request.getParameter("id"));
	svo.setWriter_id((String) session.getAttribute("sessionid"));
%>

<%
	MemberDAO mdao = new MemberDAO();

 String subject = svo.getWriter_id()+"님의 도우미로 선정되셨습니다";

 String from = "korean9850@gmail.com"; //보내는 사람
 String to = mdao.getMemberEmail(svo.getHelper_id()); //받는 사람 . 패러미터로 받은 메일주소 사용
 System.out.println(to);
 String messageText = svo.getBoard_seq()+"번 글의 도우미가 되셨습니다<br>";


String host="smtp.gmail.com", user="korean9850@gmail.com"; //메일을 보낼 정보를 지정..

String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; 


	Properties prop = new Properties();
	prop.put("mail.smtp.user", user); 
	prop.put("mail.smtp.host", "smtp.gmail.com");
	prop.put("mail.smtp.port", "465");
	prop.put("mail.smtp.starttls.enable","true");
	prop.put("mail.smtp.auth", "true");
	prop.put("mail.smtp.debug", "true");
	prop.put("mail.smtp.ssl.enable", "true");
	prop.put("mail.smtp.ssl.trust", host);
	prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	prop.put("mail.smtp.socketFactory.fallback", "false");


	Session mailSession = Session.getInstance(prop,new Authenticator(){
		public PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(user, "ocmoztobibqiekpo");
	    }
	});

    mailSession.setDebug(true);
    
    try {
        MimeMessage msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from));
    	msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    	msg.setSubject(subject);
    	msg.setContent(messageText, "text/html; charset=UTF-8");//utf-8 포맷의 html로 메일 내용 메시지 객체에 설정. 그 위의 값들도 각각 메시지의 맞는 값에 설정
        // send the message
        Transport.send(msg);
        System.out.println(to + "로 인증메일이 전송되었습니다.");
        System.out.println("message sent successfully...");
        out.println(sdao.insertMatching(svo));//메일 완료되면 디비에 삽입
    } catch (Exception e){
    	e.printStackTrace();
    	out.println("0");
    }

%> 
