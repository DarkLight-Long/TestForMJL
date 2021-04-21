function People(sex) {
    this.sex = sex;
}

function Men(name, age)  {
    this.name = name;
    this.age = age;
}

function Women(name, age) {
    this.name = name;
    this.age = age;
}
Men.prototype = new People("男");
Women.prototype = new People("女");

//方形加载特效 - 直线型加载特效

const black = document.getElementsByClassName("black")[0]
black.style.width = "10px"
black.style.height = "10px"
black.style.backgroundColor = "black"
black.style.transition = "transform 1s"
let x = 10
let y = 10;
function blackMove() {
    // if (x < 110 && y === 10) {
    //     x = x + 10;
    // } else if (x === 110 && y < 110) {
    //     y = y + 10;
    // } else if (x > 10 && y === 110) {
    //     x = x - 10;
    // } else if (x === 10 && y > 10) {
    //     y = y - 10;
    // }

    if (x === 10 && y === 10) {
        x = x + 100;
    } else if (x === 110 && y === 10) {
        y = y + 100;
    } else if (x === 110 && y === 110) {
        x = x - 100;
    } else if (x === 10 && y === 110) {
        y = y - 100;
    }
    black.style.transform = `translate(${x}px, ${y}px)`
}







