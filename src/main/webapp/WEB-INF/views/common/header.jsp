<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
 <!-- Basic -->
 <meta charset="utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=edge" />
 <!-- Mobile Metas -->
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
 <!-- Site Metas -->
 <meta name="keywords" content="" />
 <meta name="description" content="" />
 <meta name="author" content="" />
 <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/homepage_img/favicon.png" type="image/x-icon">
 <title> Hệ Thống Quản Lý Khách Sạn </title>
 <!-- bootstrap core css -->
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/homepage_css/bootstrap.css" />
 <!--owl slider stylesheet -->
 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
 <!-- nice select  -->
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css" integrity="sha512-CruCP+TD3yXzlvvijET8wV5WxxEh5H8P4cmz0RFbKK6FlZ2sYl3AEsKlLPHbniXKSrDdFewhbmBK5skbdsASbQ==" crossorigin="anonymous" />
 <!-- font awesome style -->
 <link href="${pageContext.request.contextPath}/assets/css/homepage_css/font-awesome.min.css" rel="stylesheet" />
 <!-- Custom styles for this template -->
 <link href="${pageContext.request.contextPath}/assets/css/homepage_css/style.css" rel="stylesheet" />
 <!-- responsive style -->
 <link href="${pageContext.request.contextPath}/assets/css/homepage_css/responsive.css" rel="stylesheet" />
 <script>
    window.AppConfig = {
        contextPath: "${pageContext.request.contextPath}",
        isLoggedIn: ${not empty userSession ? 'true' : 'false'}
    };
</script>
</head>