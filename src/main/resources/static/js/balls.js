function index() {
    let balls = document.getElementsByClassName("right-meta-ball");
    let container = document.getElementById("right-meta-ball-container");
    let nav = document.querySelector("nav");
    let clicked = true;

    let e = document.addEventListener("click", function(e) {
        if(!(e.target.classList.contains("meta-ball-container") || e.target.classList.contains("right-meta-ball") || e.target.classList.contains("main-nav"))) {
            balls[0].style.backgroundColor = "rgb(0,0,0)";
            balls[1].style.backgroundColor = "rgb(0,0,0)";
            balls[2].style.backgroundColor = "rgb(0,0,0)";
            balls[0].style.transform = "";
            balls[2].style.transform = "";
            balls[1].style.transform = "";
            nav.style.display = "none";

            clicked = true;
        }
    })

    container.addEventListener("click", function () {

        if (clicked) {
            balls[0].style.transform = "translateX(2.5vh)";
            balls[2].style.transform = "translateX(-2.5vh)";
            balls[1].style.transform = "scaleX(1.5)";
            balls[1].style.transform = "scaleY(1.2)";
            setTimeout(function () {
                balls[0].style.backgroundColor = "rgb(255,0,0)";
                balls[1].style.backgroundColor = "rgb(255,0,0)";
                balls[1].style.transform = "scale(1.7)";
                balls[2].style.backgroundColor = "rgb(255,0,0)";
            }, 300);
            nav.style.display = "flex";

            clicked = false;

        } else {
            balls[0].style.backgroundColor = "rgb(0,0,0)";
            balls[1].style.backgroundColor = "rgb(0,0,0)";
            balls[2].style.backgroundColor = "rgb(0,0,0)";
            balls[0].style.transform = "";
            balls[2].style.transform = "";
            balls[1].style.transform = "";
            nav.style.display = "none";

            clicked = true;

        }
    })
}

index();
