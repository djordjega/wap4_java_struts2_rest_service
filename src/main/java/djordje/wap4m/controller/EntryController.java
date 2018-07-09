package djordje.wap4m.controller;

import com.opensymphony.xwork2.ModelDriven;
import djordje.wap4m.model.Entry;
import djordje.wap4m.model.EntryDAO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author djordje gavrilovic
 */
public class EntryController implements ModelDriven<Object>, ServletRequestAware {

    HttpServletRequest request;
    private Object model;
    private EntryDAO entryDao = new EntryDAO();
    private static Map<String, Entry> map;
    private String id;

    {
        map = entryDao.getAllEntries();
    }

    /* 
    * init GET
    */
    public HttpHeaders index() {
        model = map;
        return new DefaultHttpHeaders("index").disableCaching();
    }

    /*
    * POST - add new entry
    */
    public HttpHeaders create() {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        entryDao.addNewEntry(title, text);
        return new DefaultHttpHeaders("create").disableCaching();
    }

    /* 
    * DELETE selected entry
    */
    public HttpHeaders destroy() {
        entryDao.removeEntry(id);
        return new DefaultHttpHeaders("create").disableCaching();
    }

    public void setId(String id) {
        if (id != null) {
            this.model = entryDao.getEntry(id);
        }
        this.id = id;
    }

    @Override
    public Object getModel() {
        return model;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
