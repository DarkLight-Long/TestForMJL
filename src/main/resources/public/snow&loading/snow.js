class Snow {
    constructor( opt = {}) {
        // 元素
        this.el = null;
        // 直径
        this.width = 0;
        // 最大直径
        this.maxWidth = opt.maxWidth || 80
        // 最小直径
        this.minWidth = opt.minWidth || 2
        // 透明度
        this.opacity = 0
        // 水平位置
        this.x = 0
        // 垂直位置
        this.y = 0
        // 速度
        this.speedX = 0
        this.speedY = 0
        // 最大速度
        this.maxSpeed = opt.maxSpeed || 2
        // 最小速度
        this.minSpeed = opt.minSpeed || 1
        // 屏幕宽度
        this.windowWidth = window.innerWidth
        // 屏幕高度
        this.windowHeight = window.innerHeight
        this.init()
    }

    // 初始化容器
    init(reset) {
        this.width = Math.floor(Math.random()* this.maxWidth + this.minWidth)
        this.opacity = Math.random();
        this.x = Math.floor(Math.random() * (this.windowWidth - this.width))
        this.y = Math.floor(Math.random() * (this.windowHeight - this.width))
        if (reset && Math.random() > 0.8) {
            this.x = - this.width
        } else if (reset) {
            this.y = - this.width
        }
        this.speedY = Math.random() * this.maxSpeed + this.minSpeed
        this.speedX = this.speedY * Math.random();
    }

    // 设置样式
    setStyle() {
        this.el.style.cssText = `
        position: fixed;
        left: 0;
        top: 0;
        display: block;
        width: ${this.width}px;
        height: ${this.width}px;
        opacity: ${this.opacity};
        background-image: radial-gradient(#fff 0% ,rgba(255, 255, 255, 0) 60%);
        border-radius: 50%;
        z-index: 999999999999;
        pointer-events: none;
        transform: translate(${this.x}px, ${this.y}px)
        `
    }

    // 渲染
    render() {
        this.el = document.createElement('div')
        this.setStyle();
        document.body.appendChild(this.el)
    }

    move() {
        this.x += this.speedX
        this.y += this.speedY

        if (this.x < this.width || this.x > this.windowWidth || this.y > this.windowHeight) {
            this.init(true);
            this.setStyle();
        }

        this.el.style.left = this.x + "px"
        this.el.style.top = this.y + "px"
    }



}