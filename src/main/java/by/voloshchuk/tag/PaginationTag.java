package by.voloshchuk.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class PaginationTag extends TagSupport {

    private int total;

    private int current;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (total != 1) {

                pageContext.getOut().write("<div class=\"row justify-content-center mt-1\">");
                pageContext.getOut().write("<div class=\"col-md-8\" >");
                pageContext.getOut().write("<ul class=\"pagination justify-content-center\" >");
                if (current != 1) {
                    pageContext.getOut().write("<li class=\"page-item\" ><a class=\"page-link\" href = \"?command=technical-tasks&tasksPerPage=4&currentPage=1\"> << </a></li>");
                    pageContext.getOut().write("<li class=\"page-item\" ><a class=\"page-link\" href = \"?command=technical-tasks&tasksPerPage=4&currentPage=" + (current-1) +"\" > < </a></li>");
                }

                for (int i = 1; i <= total; i++) {
                    if (current == i) {
                        pageContext.getOut().write("<li class=\"page-item active\" ><a class=\"page-link\" >" + i + "<span class=\"sr-only\" >(current)</span ></a></li>");
                    } else {
                        if ((current == i - 1) || (current == i + 1)) {
                            pageContext.getOut().write("<li class=\"page-item\" ><a class=\"page-link\" href = \"?command=technical-tasks&tasksPerPage=4&currentPage=" + i + "\">"+ i +"</a></li>");

                        }
                    }

                }

                if (current < total) {
                    pageContext.getOut().write("<li class=\"page-item\" ><a class=\"page-link\" href = \"?command=technical-tasks&tasksPerPage=4&currentPage="+(current+1)+"\" > > </a></li>");
                }
                if (current != total) {
                    pageContext.getOut().write("<li class=\"page-item\" ><a class=\"page-link\" href = \"?command=technical-tasks&tasksPerPage=4&currentPage="+total+"\" > >> </a></li>");
                }

                pageContext.getOut().write("</ul >");
                pageContext.getOut().write("</div >");
                pageContext.getOut().write("</div >");

                }


        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

}
