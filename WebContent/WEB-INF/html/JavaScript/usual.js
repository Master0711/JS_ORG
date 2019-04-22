class RegularManager
{
    static isNum (str){
        let reg = /^\d+$/g;
        return str.match(reg);
    }
    static isPhone (str){
        let reg = /^1[34578]\d{9}$/g;
        return str.match(reg);
    }
    static isEmail (str){
        let reg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/g;
        return str.match(reg);
    }
}

class UrlManager
{
    static GetUrl () {
        let url = window.location.href;  //url
        let protocol = window.location.protocol;
        let host = window.location.host;
        let port = window.location.port;
        let pathname = window.location.pathname;
        let search = window.location.search;    //参数
        let hash = window.location.hash;
        return { url: url, protocol: protocol, host: host, port: port, pathname: pathname, search: search, hash: hash};
    }
    static GetSearch () {
        let search = UrlManager.GetUrl().search;
        search = search.replace('?','');
        let unit = search.split('&');
        let searchs = [];
        for(let u of unit){
            let t = u.split('=');
            searchs[t[0].toLowerCase()] = t[1];
        }
        return searchs;
    }
    static EncodeUrl (url) {
        return encodeURIComponent(url);
    }
    static DecodeUrl (url) {
        return decodeURIComponent(url);
    }
    static isEncode () {
        if (url.indexOf("%3A%2F%2F") != -1) {
            encodeURI = url;
            url = decodeURIComponent(url);
        } else {
            encodeURI = encodeURIComponent(url);
        }
    }
}

class MSCUtility
{
    static STimeRounder(dateString)
    {
        if(dateString==null||dateString=="")
            dateString = new Date().toLocaleString();
        let now = new Date();
        let target = SDate.parse(dateString);
        let dis = SDate.DisTime(target,now);
        if(SDate.DisTime(target,now)<1000)
            return "刚刚";
        else if(dis<1000*60)
            return `${(dis/1000).toFixed(0)} 秒前`;
        else if(dis<1000*60*60)
            return `${(dis/1000/60).toFixed(0)} 分钟前`;
        else if(dis<1000*3600*6)
            return `${(dis/1000/3600).toFixed(0)} 小时前`;
        else if(dis<1000*3600*24)
        {
            if(target.getHours()-now.getHours()>0)
                return `昨天 ${target.getHours()}:${target.getMinutes()<10?"0"+target.getMinutes():target.getMinutes()}`;
            return `今天 ${target.getHours()}:${target.getMinutes()<10?"0"+target.getMinutes():target.getMinutes()}`;
        }
        else if(dis<1000*3600*48)
        {
            if(target.getDate()-now.getDate()<-1)
                return `前天 ${target.getHours()}:${target.getMinutes()<10?"0"+target.getMinutes():target.getMinutes()}`;
            return `昨天 ${target.getHours()}:${target.getMinutes()<10?"0"+target.getMinutes():target.getMinutes()}`;
        }
        else if(target.getFullYear()==now.getFullYear())
            return `${target.getMonth()+1}-${target.getDate()}`;
        else
            return `${target.getFullYear()} ${target.getMonth()+1}-${target.getDate()}`;
    }
}