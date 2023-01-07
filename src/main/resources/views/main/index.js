let MAIN = {
    initialize: function() {
        if(MAIN.isPopup()){
            if(!$.cookie("notice")){
                $('#noticeModal').modal('show');
            }
        }
    },
    onAddCardClick: function (e) {
        const jqCard = $(`
            <div class="col-sm-3 mt-2">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">제목</h5>
                        <h6 class="card-subtitle mb-2 text-muted">서브제목</h6>
                        <p class="card-text">블라블라</p>
                        <a href="#" class="card-link">Red</a>
                        <a href="#" class="card-link">Green</a>
                    </div>
                </div>
            </div>
        `);

        jqCard.hide();
        $("#cardGroupDiv").append(jqCard);
        jqCard.show("normal");
    },
    isPopup: function() {
        const todayDate = new Date();
        const dueDate = new Date('2023-01-10T05:00:00.000Z');
        return todayDate.getTime() < dueDate.getTime();
    }
};



$(document).ready(function () {
    MAIN.initialize();

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar, #content').toggleClass('active');
        $('.collapse.in').toggleClass('in');
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    });

    $("#addCardBtn").on("click", MAIN.onAddCardClick);

    $('#noticeModal').on('hidden.bs.modal', function (e) {
        const todayCloseCheck = $("#noticeTodayCloseChk").is(":checked");

        if(todayCloseCheck){
            const noticeCookie = $.cookie("notice");
            if(!noticeCookie){
                $.cookie("notice", true, { expires: 1, path: '/' });
            }
        }
    })

    console.log(CURRENT_ENV);
    console.log(OTHER_SERVICE_URL);
});