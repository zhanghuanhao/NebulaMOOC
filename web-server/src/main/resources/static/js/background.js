function Star(id, x, y) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.r = Math.floor(Math.random() * 2) + 1;
    var alpha = (Math.floor(Math.random() * 10) + 1) / 10 / 2;
    this.color = "rgba(255,255,255," + alpha + ")";
}

Star.prototype.draw = function () {
    ctx.fillStyle = this.color;
    ctx.shadowBlur = this.r * 2;
    ctx.beginPath();
    ctx.arc(this.x, this.y, this.r, 0, 2 * Math.PI, false);
    ctx.closePath();
    ctx.fill();
};

Star.prototype.move = function () {
    this.y -= .15;
    if (this.y <= -10) this.y = HEIGHT + 10;
    this.draw();
};

Star.prototype.die = function () {
    stars[this.id] = null;
    delete stars[this.id];
};


function Dot(id, x, y, r) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.r = Math.floor(Math.random() * 5) + 1;
    this.speed = .7;
    this.a = .5;
    this.aReduction = .01;
    this.color = "rgba(255,255,255," + this.a + ")";
    this.linkColor = "rgba(255,255,255," + this.a / 4 + ")";

    this.dir = Math.floor(Math.random() * 140) + 200;
}

Dot.prototype.draw = function () {
    ctx.fillStyle = this.color;
    ctx.shadowBlur = this.r * 2;
    ctx.beginPath();
    ctx.arc(this.x, this.y, this.r, 0, 2 * Math.PI, false);
    ctx.closePath();
    ctx.fill();
};

Dot.prototype.link = function () {
    if (this.id == 0) return;
    var previousDot1 = getPreviousDot(this.id, 1);
    var previousDot2 = getPreviousDot(this.id, 2);
    var previousDot3 = getPreviousDot(this.id, 3);
    if (!previousDot1) return;
    ctx.strokeStyle = this.linkColor;
    ctx.moveTo(previousDot1.x, previousDot1.y);
    ctx.beginPath();
    ctx.lineTo(this.x, this.y);
    if (previousDot2 != false) ctx.lineTo(previousDot2.x, previousDot2.y);
    if (previousDot3 != false) ctx.lineTo(previousDot3.x, previousDot3.y);
    ctx.stroke();
    ctx.closePath();
};

function getPreviousDot(id, stepback) {
    if (id == 0 || id - stepback < 0) return false;
    if (typeof dots[id - stepback] != "undefined") return dots[id - stepback];
    else return false;
}

Dot.prototype.move = function () {
    this.a -= this.aReduction;
    if (this.a <= 0) {
        this.die();
        return;
    }
    this.color = "rgba(255,255,255," + this.a + ")";
    this.linkColor = "rgba(255,255,255," + this.a / 4 + ")";
    this.x = this.x + Math.cos(degToRad(this.dir)) * this.speed;
    this.y = this.y + Math.sin(degToRad(this.dir)) * this.speed;

    this.draw();
    this.link();
};

Dot.prototype.die = function () {
    dots[this.id] = null;
    delete dots[this.id];
};


var canvas = document.getElementById('canvas'),
    ctx = canvas.getContext('2d'),
    WIDTH,
    HEIGHT,
    mouseX,
    mouseY,
    pX,
    pY,
    stars = [],
    initStarsPopulation = 30,
    dots = [],
    dotsMinDist = 8,
    maxDistFromCursor = 60;

setCanvasSize();
init();

function setCanvasSize() {

    WIDTH = window.screen.width;
    HEIGHT = window.screen.height;

    canvas.setAttribute("width", WIDTH);
    canvas.setAttribute("height", HEIGHT);

}

function init() {

    ctx.strokeStyle = "white";
    ctx.shadowColor = "white";
    for (var i = 0; i < initStarsPopulation; i++) {
        stars[i] = new Star(i, Math.floor(Math.random() * WIDTH), Math.floor(Math.random() * HEIGHT));
        stars[i].draw();
    }
    ctx.shadowBlur = 0;
    pX = 0;
    pY = 0;
    animate();
}

function animate() {
    ctx.clearRect(0, 0, WIDTH, HEIGHT);

    for (var i in stars) {
        stars[i].move();
    }
    for (var i in dots) {
        dots[i].move();
    }
    drawIfMouseMoving();
    requestAnimationFrame(animate);
}

window.onmousemove = function (e) {
    mouseX = e.clientX;
    mouseY = e.clientY;
};


function drawIfMouseMoving() {
    if (pX == mouseX && pY == mouseY) {
        return;
    } else {
        pX = mouseX;
        pY = mouseY;
    }

    if (dots.length == 0) {
        dots[0] = new Dot(0, mouseX, mouseY);
        dots[0].draw();
        return;
    }

    var previousDot = getPreviousDot(dots.length, 1);
    var prevX = previousDot.x;
    var prevY = previousDot.y;

    var diffX = Math.abs(prevX - mouseX);
    var diffY = Math.abs(prevY - mouseY);

    if (diffX < dotsMinDist || diffY < dotsMinDist) return;

    var xVariation = Math.random() > .5 ? -1 : 1;
    xVariation = xVariation * Math.floor(Math.random() * maxDistFromCursor) + 1;
    var yVariation = Math.random() > .5 ? -1 : 1;
    yVariation = yVariation * Math.floor(Math.random() * maxDistFromCursor) + 1;
    dots[dots.length] = new Dot(dots.length, mouseX + xVariation, mouseY + yVariation);
    dots[dots.length - 1].draw();
    dots[dots.length - 1].link();
}

function degToRad(deg) {
    return deg * (Math.PI / 180);
}


function mouseover(d) {
    var se = document.getElementById(d);
    if (se.className != "selected") {
        se.style.backgroundColor = "rgba(0,0,0,0.2)";
    }
}

function mouseout(d) {
    var se = document.getElementById(d);
    if (se.className != "selected") se.style.backgroundColor = "transparent";
}


//分页
function myPagination(_ref) {
    var pageSize = _ref.pageSize,
        pageTotal = _ref.pageTotal,
        curPage = _ref.curPage,
        id = _ref.id,
        getPage = _ref.getPage,
        showPageTotalFlag = _ref.showPageTotalFlag,
        showSkipInputFlag = _ref.showSkipInputFlag,
        dataTotal = _ref.dataTotal;

    this.pageSize = pageSize || 10; //分页个数
    this.pageTotal = pageTotal; //总共多少页
    this.dataTotal = dataTotal; //总共多少数据
    this.curPage = curPage || 1; //初始页码
    this.ul = document.createElement('ul');
    this.id = id;
    this.getPage = getPage;
    this.showPageTotalFlag = showPageTotalFlag || false; //是否显示数据统计
    this.showSkipInputFlag = showSkipInputFlag || false; //是否支持跳转
    this.init();
}

// 给实例对象添加公共属性和方法
myPagination.prototype = {
    init: function init() {
        var pagination = document.getElementById(this.id);
        pagination.innerHTML = '';
        this.ul.innerHTML = '';
        pagination.appendChild(this.ul);
        var that = this;
        //首页
        that.firstPage();
        //上一页
        that.lastPage();
        //分页
        that.getPages().forEach(function (item) {
            var li = document.createElement('li');
            if (item == that.curPage) {
                li.className = 'active';
            } else {
                li.onclick = function () {
                    that.curPage = parseInt(this.innerHTML);
                    that.init();
                    that.getPage(that.curPage);
                };
            }
            li.innerHTML = item;
            that.ul.appendChild(li);
        });
        //下一页
        that.nextPage();
        //尾页
        that.finalPage();

        //是否支持跳转
        if (that.showSkipInputFlag) {
            that.showSkipInput();
            that.skip();
        }
        //是否显示总页数,每页个数,数据
        if (that.showPageTotalFlag) {
            that.showPageTotal();
        }
    },
    //首页
    firstPage: function firstPage() {
        var that = this;
        var li = document.createElement('li');
        li.innerHTML = '首页';
        this.ul.appendChild(li);
        li.onclick = function () {
            var val = parseInt(1);
            that.curPage = val;
            that.getPage(that.curPage);
            that.init();
        };
    },
    //上一页
    lastPage: function lastPage() {
        var that = this;
        var li = document.createElement('li');
        li.innerHTML = '<';
        if (parseInt(that.curPage) > 1) {
            li.onclick = function () {
                that.curPage = parseInt(that.curPage) - 1;
                that.init();
                that.getPage(that.curPage);
            };
        } else {
            li.className = 'disabled';
        }
        this.ul.appendChild(li);
    },
    //分页
    getPages: function getPages() {
        var pag = [];
        if (this.curPage <= this.pageTotal) {
            if (this.curPage < this.pageSize) {
                //当前页数小于显示条数
                var i = Math.min(this.pageSize, this.pageTotal);
                while (i) {
                    pag.unshift(i--);
                }
            } else {
                //当前页数大于显示条数
                var middle = this.curPage - Math.floor(this.pageSize / 2),
                    //从哪里开始
                    i = this.pageSize;
                if (middle > this.pageTotal - this.pageSize) {
                    middle = this.pageTotal - this.pageSize + 1;
                }
                while (i--) {
                    pag.push(middle++);
                }
            }
        }
        if (!this.pageSize) {
            console.error('显示页数不能为空或者0');
        }
        return pag;
    },
    //下一页
    nextPage: function nextPage() {
        var that = this;
        var li = document.createElement('li');
        li.innerHTML = '>';
        if (parseInt(that.curPage) < parseInt(that.pageTotal)) {
            li.onclick = function () {
                that.curPage = parseInt(that.curPage) + 1;
                that.init();
                that.getPage(that.curPage);
            };
        } else {
            li.className = 'disabled';
        }
        this.ul.appendChild(li);
    },
    //尾页
    finalPage: function finalPage() {
        var that = this;
        var li = document.createElement('li');
        li.innerHTML = '尾页';
        this.ul.appendChild(li);
        li.onclick = function () {
            var yyfinalPage = that.pageTotal;
            var val = parseInt(yyfinalPage);
            that.curPage = val;
            that.getPage(that.curPage);
            that.init();
        };
    },
    //是否支持跳转
    showSkipInput: function showSkipInput() {
        var that = this;
        var li = document.createElement('li');
        li.className = 'totalPage';
        var span1 = document.createElement('span');
        span1.innerHTML = '跳转到';
        li.appendChild(span1);
        var input = document.createElement('input');
        input.setAttribute("type", "number");
        input.setAttribute("id", "numinput");
        li.appendChild(input);
        var span2 = document.createElement('span');
        span2.innerHTML = '页';
        li.appendChild(span2);
        this.ul.appendChild(li);
    },
    skip: function showSkip() {
        var that = this;
        var li = document.createElement('li');
        li.innerHTML = '确认';
        this.ul.appendChild(li);
        li.onclick = function () {
            var val = parseInt(document.getElementById("numinput").value);
            if (typeof val === 'number' && val <= that.pageTotal) {
                that.curPage = val;
                that.getPage(that.curPage);
            } else {
                that.curPage = 1;
                that.getPage(that.curPage);
            }
            that.init();
        };
    },
    //是否显示总页数,每页个数,数据
    showPageTotal: function showPageTotal() {
        var that = this;
        var li = document.createElement('li');
        li.innerHTML = '共&nbsp' + that.pageTotal + '&nbsp页';
        li.className = 'totalPage';
        this.ul.appendChild(li);
    }
};




