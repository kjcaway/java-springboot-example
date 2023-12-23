let MAIN = {
  init: function () {
    getMember();
  },
  showLeftSelectedNode: function (data) {
    $("#info-node").html("");
    $("#info-node").html(`
            <h5>${data.text}</h5>
            <ui>
                <li>id : ${data.nodeId}</li>
                <li>desc : ${data.desc}</li>
        `);
  },
  hideLeftSelectedNode: function (data) {
    $("#info-node").html("");
  }
};

function getMember() {
  const domain = API_DOMAIN;
  const path = "/api/v1/member/paging"
  $.get(`http://${domain}/${path}`, function (res) {
    // Assuming the API returns an array of member objects
    const members = res.data.list;
    const totalcount = res.data.totalcount;

    // Loop through the members and populate the table body
    for (let i = 0; i < members.length; i++) {
      const no = totalcount - i;
      $("#member-tbody").append("<tr>" +
          "<td>" + no + "</td>" +
          "<td>" + members[i].name + "</td>" +
          "<td>" + members[i].email + "</td>" +
          "<td>" + members[i].categoryName + "</td>" +
          "</tr>");
    }
  });
}

$(document).ready(function () {
  $('#sidebarCollapse').on('click', function () {
    $('#sidebar, #content').toggleClass('active');
    $('.collapse.in').toggleClass('in');
    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
  });

  MAIN.init();
});