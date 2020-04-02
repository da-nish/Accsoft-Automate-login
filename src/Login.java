import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Login {

    private static String URL_stlg = "http://portal.lnct.ac.in/Accsoft2/StudentLogin.aspx";
    private static String URL_lg = "http://portal.lnct.ac.in/Accsoft2/Login.aspx";
    private static String HOST = "http://portal.lnct.ac.in/";

    public static void main(String[] args) {

        String acc_ID = "ACC ID";
        String acc_pass = "ACC PASS";

        doLogin(acc_ID, acc_pass);
    }

    private static void doLogin(String acc_ID, String acc_pass ){

        System.out.println("Connecting AccSoft...");
        try{
            Response response =
                    Jsoup.connect(URL_stlg)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36Mozilla/5.0")
                            .timeout(0)
                            .method(Method.POST)
                            .data("__EVENTTARGET","")
                            .data("__EVENTARGUMENT" ,"")
                            .data("__LASTFOCUS","")
                            .data("__VIEWSTATE","/wEPDwUJLTk3NzUxOTMxD2QWAmYPZBYCAgMPZBYEAgcPZBYGAgcPZBYCZg9kFgICAQ8QZGQWAWZkAgkPZBYCZg9kFgICAQ8WAh4HVmlzaWJsZWcWAgIED2QWAmYPZBYCAgEPZBYCZg9kFgICAQ9kFgRmD2QWAmYPZBYCAgEPZBYCZg9kFgICAQ8QZGQWAGQCAQ9kFgJmD2QWAgIDDxYCHgVWYWx1ZQUCNTFkAgsPZBYCZg9kFgICAQ8WAh8AaBYCAgQPZBYCZg9kFgICAQ9kFgJmD2QWAgIBD2QWBGYPZBYCZg9kFgICAQ9kFgJmD2QWAgIBDxAPFgYeDURhdGFUZXh0RmllbGQFB0ZpblllYXIeDkRhdGFWYWx1ZUZpZWxkBQlGaW5ZZWFySUQeC18hRGF0YUJvdW5kZ2QQFQ8KLS1TZWxlY3QtLQoyMDE5LTIwMjAgCjIwMTgtMjAxOSAKMjAxNy0yMDE4IAoyMDE2LTIwMTcgCjIwMTUtMjAxNiAKMjAxNC0yMDE1IAoyMDEzLTIwMTQgCjIwMTItMjAxMyAKMjAxMS0yMDEyIAoyMDEwLTIwMTEgCjIwMDktMjAxMCAKMjAwOC0yMDA5IAoyMDA3LTIwMDggCjIwMDYtMjAwNyAVDwEwAjE0AjEzAjEyAjExAjEwATkBOAE3ATYBNQE0ATMBMgExFCsDD2dnZ2dnZ2dnZ2dnZ2dnZxYBZmQCAQ9kFgJmD2QWAgIBD2QWAmYPZBYCAgEPEGRkFgBkAgkPDxYCHgRUZXh0BQswNy1NYXItMjAyMGRkZLKntlu9gyuOHzSIQb7kofyNJGy/wXYNxBbxbZ3F5w3q")
                            .data("__ASYNCPOST", "true")
                            .data("__EVENTVALIDATION","/wEdAAY9lr8ApE/6L9beFG92jnSJseDjO4OjzzusyZ26WwJoA+zQjwyf5g+4ezBNg2ywlcRWRY5uqcphA2smPJPFzLHn+mo9SV2v/SHtiocBMWq7cO7ou5vayuKtr5/nHS8pGkX1gxBx/apOLmAUT3RZxpJoMu7u5MGqXkYdwg+U8KzjYw==")
                            .data("ctl00$cph1$txtStuUser", acc_ID)
                            .data("ctl00$cph1$txtStuPsw", acc_pass)
                            .data("ctl00$cph1$btnStuLogin", "Login >>")
                            .followRedirects(true)
                            .execute();

            Document page = Jsoup
                    .connect("http://portal.lnct.ac.in/Accsoft2/Parents/ParentDesk.aspx")
                    .cookies(response.cookies()) //use this to parse any page. it will log in
                    .get();

            if(URL_lg.equals(page.location()) || URL_stlg.equals(page.location())){

                System.out.println("Authentication fail :"+ acc_ID+"-"+acc_pass);
                System.out.println("current url : "+page.location());

            }else {
                System.out.println("Authentication pass");
                System.out.println("current url : "+page.location());
                //System.out.println("HTML Document: "+page);
            }

        }catch(IOException ioe){
            System.out.println("Connection fail");
            System.out.println("Exception: " + ioe);
        }

    }
}
