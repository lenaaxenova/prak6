$(document).ready(function () {
    $('a[href="' + current_location + '"]', '.nav li').closest('li').addClass('active').siblings('.active').removeClass();
});
