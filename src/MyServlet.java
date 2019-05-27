import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.regex.*;

@WebServlet(name = "simpleServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class MyServlet extends HttpServlet {
    private String address, name, company_id;

    /**
     * doGet
     * This function accepts a get request and calls all functions to validate the request.
     * Parameters:
     * req - an HttpServletRequest object that contains the request the client has made of the servlet.
     * resp - an HttpServletResponse object that contains the response the servlet sends to the client.*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        address = getAddress(req);
        if (isCompare()) {
            System.out.println("Address: " + address);
            if (companyIdIsRight(resp))
                System.out.println("Company_ID: " + company_id);
            if (nameIsRight(resp))
                System.out.println("User's name: " + name);
        } else {
            resp.sendError(resp.SC_NOT_FOUND, "Don't Found such element!!!");
        }
    }

    /**
     * getAddress
     * This function checks the string request.
     * Parameters:
     * req - an HttpServletRequest object that contains the request the client has made of the servlet.
     * Returns:
     * the HTTP request method*/
    private String getAddress(HttpServletRequest req) {
        return req.getQueryString() == null ? req.getRequestURL().toString() :
                req.getRequestURL().toString() + "?" + req.getQueryString();
    }

    /**
     * isCompare
     * This function checks get request using regular expressions as a template.
     * Returns:
     * true if count == 1, else false.*/
    private boolean isCompare() {
        int count = 0;
        Matcher matcher = Pattern.compile("/\\w++\\.\\w++/company/\\d{1,10}/users\\?name=\\w++").matcher(address);

        while (matcher.find())
            count++;
        return count == 1;
    }

    /**
     * nameIsRight
     * This function checks the spelling of the user's name.
     * Parameters:
     * resp - an HttpServletResponse object that contains the response the servlet sends to the client.
     * Returns:
     * true if name.equals(data), else return false.*/
    private boolean nameIsRight(HttpServletResponse resp) throws IOException {
        Matcher matcher = Pattern.compile("/users\\?name=([a-zA-Z]++)\\Z").matcher(address);
        if (matcher.find()) {
            name = address.substring(matcher.start() + 12, matcher.end());
            if (isExists("name", name))
                return true;
            else {
                resp.sendError(resp.SC_NOT_FOUND, "User doesn't exist!!!");
                return false;
            }
        } else {
            resp.sendError(resp.SC_NOT_FOUND, "Wrong! User's name doesn't have numbers!!!");
            return false;
        }
    }

    /**
     * companyIdIsRight
     * This function checks the spelling of the company_id.
     * Parameters:
     * resp - an HttpServletResponse object that contains the response the servlet sends to the client.
     * Returns:
     * false if company_id.equals(data), else return true.*/
    private boolean companyIdIsRight(HttpServletResponse resp) throws IOException {
        Matcher matcher = Pattern.compile("/\\d{1,10}/").matcher(address);
        if (matcher.find()) {
            company_id = address.substring(matcher.start() + 1, matcher.end() - 1);
            if (isExists("company_id", company_id)) {
                switch (company_id) {
                    case "555":
                        resp.sendError(resp.SC_NOT_FOUND, "HERETIC ANTHEM!!!");
                        break;
                    case "666":
                        resp.sendError(resp.SC_NOT_FOUND, "GOT DAMN!!!");
                        break;
                    case "777":
                        resp.sendError(resp.SC_NOT_FOUND, "OMG!!!");
                        break;
                    default:
                        resp.sendError(resp.SC_NOT_FOUND, "Company_ID doesn't exist!!!");
                        break;
                }
                return false;
            } else
                return true;
        } else {
            resp.sendError(resp.SC_NOT_FOUND, "Company_ID doesn't exist!!!");
            return false;
        }
    }

    /**
     * isExists
     * This function checks for the existence of parameters.
     * Parameters:
     * filename - an name of file.
     * param - an name param.
     * Returns:
     * true if line.equals(param), else false.*/
    private boolean isExists(String filename, String param) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\zayil\\IdeaProjects\\WebServer\\data\\" + filename + ".txt")));
        String line = reader.readLine();
        while (line != null) {
            if (line.equals(param))
                return true;
            line = reader.readLine();
        }
        return false;
    }
}