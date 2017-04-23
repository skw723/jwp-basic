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
            error: onAddError,
            success: onAddSuccess
        });
    }

    function onAddSuccess(data) {
        var answerTemplate = $("#answerTemplate").html();
        var template = answerTemplate.format(data.answer.answerId, data.answer.writer, data.answer.contents, new Date(data.answer.createdDate), data.answer.questionId);
        $(template).insertBefore(".qna-comment-slipp-articles .submit-write");
        var newCount = $(".qna-comment-slipp-articles article").length | 0;
        $("#countOfAnswer").html(newCount);
        $("#writer").val("");
        $("#contents").val("");
    }

    function onAddError(data) {
        alert("등록실패");
    }

    String.prototype.format = function() {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function(match, number) {
            return typeof args[number] != 'undefined' ? args[number] : match;
        });
    }

    $(".qna-comment-slipp-articles").on("click", ".form-delete button[type=submit]", deleteAnswer);
    function deleteAnswer(e) {
        e.preventDefault();
        var queryString = $(e.currentTarget).parent(".form-delete").serialize();

        var target = e.currentTarget;

        $.ajax({
            type: "post",
            url: "/api/qna/deleteAnswer",
            data: queryString,
            dataType: "json",
            error: onDeleteError,
            success: onDeleteSuccess.bind(target)
        });
    }

    function onDeleteSuccess(data) {
        if (data.result.isSuccess) {
            $(this).parents("article").remove();
            var newCount = $(".qna-comment-slipp-articles article").length | 0;
            $("#countOfAnswer").html(newCount);
        } else {
            alert(data.result.message);
        }
    }

    function onDeleteError(data) {
        alert(data.result.message);
    }
});