var $boxes = document.querySelectorAll('#R, #G, #B');

var anuntaCount = 0;
var anunta = function() {
    if (anuntaCount > 0) {
        return;
    } else {
        anuntaCount++;
        console.log("castigatorul este " + this.id);
    }
};

setTimeout(function() {
    for (var i = 0; i < $boxes.length; i++) {
        $boxes[i].addEventListener('transitionend', anunta);

        var duration = 2 + (Math.random() * 6000 | 0) / 1000;
        $boxes[i].style.transitionDuration = duration + "s";
        console.log($boxes[i].id + " merge cu viteza " + duration);

        $boxes[i].className += ' startRace';
    }
}, 400);
