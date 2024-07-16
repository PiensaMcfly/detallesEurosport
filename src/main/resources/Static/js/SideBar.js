document.getElementById("sidebarToggle").addEventListener("click", function() {
    var sidebar = document.getElementById("sidebar");
    if (sidebar.style.marginLeft === "-250px") {
        sidebar.style.marginLeft = "0px";
        document.getElementById("viewport").style.paddingLeft = "250px";
    } else {
        sidebar.style.marginLeft = "-250px";
        document.getElementById("viewport").style.paddingLeft = "0px";
    }
});