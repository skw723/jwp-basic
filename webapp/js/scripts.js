$(document).ready(function () {/* jQuery toggle layout */
    $('#btnToggle').click(function () {
        if ($(this).hasClass('on')) {
            $('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
            $(this).removeClass('on');
        }
        else {
            $('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
            $(this).addClass('on');
        }
    });

    $(".submit-write button.btn-success").click(addAnswer);

    function addAnswer(e) {
        e.preventDefault();

        var queryString = $(".submit-write").serialize();

        $.ajax({
            type: "post",
            url: "/api/qna/addAnswer",
            data: queryString,
            dataType: "json",
            error: onError,
            success: onSuccess
        });
    }

    function onSuccess(data) {
        var answerTemplate = $("#answerTemplate").html();
        var template = answerTemplate.format(data.answerId, data.writer, data.contents, new Date(data.createdDate), data.questionId);
        $(template).insertBefore(".qna-comment-slipp-articles .submit-write");
        var newCount = $(".qna-comment-slipp-articles article").length | 0;
        $("#countOfAnswer").html(newCount);
        $("#writer").val("");
        $("#contents").val("");
    }

    function onError(data) {
        alert("등록실패");
    }

    String.prototype.format = function() {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function(match, number) {
            return typeof args[number] != 'undefined' ? args[number] : match;
        });
    }
});