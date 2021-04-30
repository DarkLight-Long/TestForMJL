class CircleLoading {
    constructor() {
        this.boxEl = null
        this.loadEl = null

        this.boxWidth = 100
        this.boxHeight = 100
        this.boxBackgroundColor = 'transparent'
        this.boxRotate = 0

        this.loadWidth = 20
        this.loadHeight = 20
        this.loadborderRadius = 50
        this.loadMarginTop = 15
        this.loadMarginLeft = 40

        this.loadBackgroundColor = 'pink'
    }

    render() {
        this.boxEl = document.createElement("div")
        this.loadEl = document.createElement("div")

        this.setStyle()

        this.boxEl.appendChild(this.loadEl)
        document.body.appendChild(this.boxEl)

    }

    setStyle() {
        this.boxEl.style.width = this.boxWidth + 'px'
        this.boxEl.style.height = this.boxHeight + 'px'
        this.boxEl.style.backgroundColor = this.boxBackgroundColor
        this.boxEl.style.transition = "transform 1s"
        this.boxEl.style.position = 'absolute'

        this.loadEl.style.width = this.loadWidth + 'px'
        this.loadEl.style.height = this.loadHeight + 'px'
        this.loadEl.style.borderRadius = this.loadborderRadius + "%"
        this.loadEl.style.backgroundColor = this.loadBackgroundColor
        this.loadEl.style.marginTop = this.loadMarginTop + 'px'
        this.loadEl.style.marginLeft = this.loadMarginLeft + 'px'
        this.loadEl.style.position = 'relative'
    }
    // transform-origin 可以指定翻转支点，配合 rotate使用

    move() {
        this.boxRotate = this.boxRotate + 360
        this.boxEl.style.transform = `rotate(${this.boxRotate}deg)`
    }

}