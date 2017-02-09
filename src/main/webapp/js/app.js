var Const = (function () {
    function Const() {
    }
    return Const;
}());
Const.DEBUG = true;
var Main = (function () {
    function Main() {
    }
    Main.prototype.Main = function () {
    };
    Main.main = function () {
        console.log("test");
    };
    return Main;
}());
function main() {
    Main.main();
}
