<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="/include/header.jspf" %>
</head>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-12">
        <div class="panel panel-default">
            <header class="qna-header">
                <h2 class="qna-title">${qna.title}</h2>
            </header>
            <div class="content-main">
                <article class="article">
                    <div class="article-header">
                        <div class="article-header-thumb">
                            <img src="https://graph.facebook.com/v2.3/100000059371774/picture"
                                 class="article-author-thumb" alt="">
                        </div>
                        <div class="article-header-text">
                            <a href="/users/${qna.writer}" class="article-author-name">${qna.writer}</a>
                            <a href="/questions/${qna.questionId}" class="article-header-time" title="퍼머링크">
                                <fmt:formatDate value="${qna.createdDate}" var="formattedDate" type="date" pattern="yyyy-MM-dd HH:mm:ss" />${formattedDate}
                                <i class="icon-link"></i>
                            </a>
                        </div>
                    </div>
                    <div class="article-doc">
                        ${qna.contents}
                    </div>
                    <div class="article-util">
                        <ul class="article-util-list">
                            <li>
                                <a class="link-modify-article" href="/questions/${qna.questionId}/form">수정</a>
                            </li>
                            <li>
                                <form class="form-delete" action="/questions/${qna.questionId}" method="POST">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <button class="link-delete-article" type="submit">삭제</button>
                                </form>
                            </li>
                            <li>
                                <a class="link-modify-article" href="/home.jsp">목록</a>
                            </li>
                        </ul>
                    </div>
                </article>

                <div class="qna-comment">
                    <div class="qna-comment-slipp">
                        <p class="qna-comment-count"><strong id="countOfAnswer">${qna.countOfAnswer}</strong>개의 의견</p>
                        <div class="qna-comment-slipp-articles">
                            <c:forEach var="one" items="${answer}">
                            <article class="article" id="${one.answerId}">
                                <div class="article-header">
                                    <div class="article-header-thumb">
                                        <img src="https://graph.facebook.com/v2.3/1324855987/picture"
                                             class="article-author-thumb" alt="">
                                    </div>
                                    <div class="article-header-text">
                                        <a href="/users/1/${one.writer}" class="article-author-name">${one.writer}</a>
                                        <a href="#${one.answerId}" class="article-header-time" title="퍼머링크">
                                            <fmt:formatDate value="${qna.createdDate}" var="formattedDate" type="date" pattern="yyyy-MM-dd HH:mm:ss" />${formattedDate}
                                        </a>
                                    </div>
                                </div>
                                <div class="article-doc comment-doc">
                                    ${one.contents}
                                </div>
                                <div class="article-util">
                                    <ul class="article-util-list">
                                        <li>
                                            <a class="link-modify-article"
                                               href="/questions/413/answers/1405/form">수정</a>
                                        </li>
                                        <li>
                                            <form class="form-delete" action="/questions/${qna.questionId}/answers/${one.answerId}"
                                                  method="POST">
                                                <input type="hidden" name="_method" value="DELETE">
                                                <button type="submit" class="link-delete-article">삭제</button>
                                            </form>
                                        </li>
                                    </ul>
                                </div>
                            </article>
                            </c:forEach>
                            <form class="submit-write">
                                <input type="hidden" value="${qna.questionId}" name="questionId" id="questionId">
                                <div class="form-group" style="padding:14px;">
                                    <input class="form-control" id="writer" name="writer" placeholder="이름">
                                    <textarea class="form-control" placeholder="Update your status" name="contents" id="contents"></textarea>
                                </div>
                                <button class="btn btn-success pull-right" type="button">Post</button>
                                <div class="clearfix"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/template" id="answerTemplate">
    <article class="article">
        <div class="article-header">
            <div class="article-header-thumb">
                <img src="https://graph.facebook.com/v2.3/1324855987/picture"
                     class="article-author-thumb" alt="">
            </div>
            <div class="article-header-text">
                <a href="/users/1/{1}" class="article-author-name">{1}</a>
                <a href="{0}" class="article-header-time" title="퍼머링크">
                    {3}
                </a>
            </div>
        </div>
        <div class="article-doc comment-doc">
            {2}
        </div>
        <div class="article-util">
            <ul class="article-util-list">
                <li>
                    <a class="link-modify-article"
                       href="/questions/413/answers/1405/form">수정</a>
                </li>
                <li>
                    <form class="form-delete" action="/questions/{4}/answers/{0}"
                          method="POST">
                        <input type="hidden" name="_method" value="DELETE">
                        <button type="submit" class="link-delete-article">삭제</button>
                    </form>
                </li>
            </ul>
        </div>
    </article>
</script>

<%@ include file="/include/footer.jspf" %>
<script type="text/javascript" src="/js/scripts.js"/>
</body>
</html>