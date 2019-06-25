package com.spring.springcloudlibraryproduct.Controller;


import com.spring.springcloudlibraryproduct.Service.BookService;
import com.spring.springcloudlibraryproduct.Service.EmpService;
import com.spring.springcloudlibraryproduct.Service.libraryServiceImpl;
import com.spring.springcloudlibraryproduct.Service.noticeService;
import com.spring.springcloudlibraryproduct.pojo.*;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.image.ShortInterleavedRaster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/library")
public class ProductController {

    /*@Autowired
    private libraryServiceImpl librarySerivce;*/
    @Autowired
    private EmpService empService;

    @Autowired
    private BookService bookService;

    @Autowired
    private com.spring.springcloudlibraryproduct.Service.noticeService noticeService;

    @RequestMapping(value = "dologin")
    public String dologin(){
        return "html/Login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password
                , HttpSession session, Model model){
        session.setAttribute("message","");
        System.out.println("进入login.................");
        Integer login = empService.login(username, password);
        if (login==1) {
            System.out.println("欢迎你:"+username);
            model.addAttribute("USER",username);
            return "html/index";
        }else if (login==4) {
            session.setAttribute("message","服务熔断!");
            return "html/Login";
        }else {
            session.setAttribute("message","登录失败,账号或密码错误!!");
            return "html/Login";
        }
    }
    @RequestMapping(value = "doindex")
    public String doindex(){
        return "html/index";
    }

    @SuppressWarnings("all")
    @RequestMapping(value = "doPerson",method = RequestMethod.GET)
    public String doPerson(Model model, @RequestParam(value = "empName", required = false)String empName
                , @RequestParam(value = "pageNo", required = false)Integer pageNo){
        System.out.println("empName:"+empName);
        employee employeea = empService.getEmpByname(empName);
        List<notice> noticeList = noticeService.getAllnotice();
        int Count = empService.getEmpCount(employeea.getEmp_id(),null,null,null);
        if (pageNo==null) {
            pageNo=1;
        }
        fenye fenye = new fenye();
        fenye.setPageSize(4);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(Count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<attendance> attendancelist = empService.getattendByid(employeea.getEmp_id(),fenye.getPageSize()
                ,(fenye.getCurrentPageNo()-1)*fenye.getPageSize());
        model.addAttribute("employeea", employeea);
        model.addAttribute("attendancelist", attendancelist);
        model.addAttribute("fenye", fenye);
        model.addAttribute("noticeList", noticeList);
        return "html/Personalinformation";
    }
    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "/dofenye")
    public Object doPersona(@RequestParam(value = "empName", required = false)String empName
            , @RequestParam(value = "pageNo", required = false)Integer pageNo, @RequestParam(value = "prefixdate",required = false)String prefixdate
            ,@RequestParam(value = "suffixdate", required = false)String suffixdate,@RequestParam(value = "daka_state",required = false)Integer daka_state){
        if (pageNo==null) {
            pageNo=1;
        }
        employee employeea = empService.getEmpByname(empName);
        int Count = empService.getEmpCount(employeea.getEmp_id(), prefixdate, suffixdate, daka_state);
        fenye fenye = new fenye();
        fenye.setPageSize(4);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(Count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<attendance> attendancelist = empService.getattendBycondition(employeea.getEmp_id(),fenye.getPageSize()
                ,(fenye.getCurrentPageNo()-1)*fenye.getPageSize(), prefixdate, suffixdate, daka_state);
        Map<String,Object> attendMap = new HashMap<>();
        attendMap.put("attendancelist",attendancelist);
        attendMap.put("fenye",fenye);
        return attendMap;
    }
    @RequestMapping(value = "donotice")
    public synchronized String donotice(@RequestParam(value = "empName",required = false)String empName, Model model){
        employee employeea = empService.getEmpByname(empName);
        model.addAttribute("employeea", employeea);
        return "html/notice";
    }
    @ResponseBody
    @RequestMapping(value = "/daka", method = RequestMethod.GET)
    public int getDate(@RequestParam(value = "datetime",required = false)String datetime
            ,@RequestParam(value = "username",required = false)String username,@RequestParam(value = "empid",required = false)Integer empid
            ,@RequestParam(value = "hour",required = false)Integer hour,@RequestParam("timea")String timea){
        String [] datetimes = datetime.split("-");
        System.out.println("年:"+datetimes[0]);
        System.out.println("月:"+datetimes[1]);

        return empService.daka(datetime,username,empid,hour,timea);
    }

    @ResponseBody
    @RequestMapping(value = "/addleave",method = RequestMethod.POST)
    public Object addleave(leave leave){
        int result = empService.addleave(leave);
        if (result==1) {
            return result;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/deleta_notice", method = RequestMethod.GET)
    public Object deleta_notice(@RequestParam(value = "notice_id")String notice_id){
        int result = empService.deleta_notice(notice_id);
        List<notice> noticeList = noticeService.getAllnotice();
        Map<String, Object> noticeMap = new HashMap<>();
        noticeMap.put("noticeList", noticeList);
        noticeMap.put("result", result);
        return noticeMap;
    }
    @SuppressWarnings("all")
    @RequestMapping(value = "doemp", method = RequestMethod.GET)
    public String doemp(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName,@RequestParam(value = "pageNo", required = false)Integer pageNo
            ,@RequestParam(value = "roleID",required = false)String roleID,Model model){
        int Count = empService.getCount(entry_starttime, entry_prefixtime, empName, roleID);
        if (pageNo==null) {
            pageNo=1;
        }
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(Count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<employee> employeeList = empService.getAllEmp(entry_starttime, entry_prefixtime, empName, roleID,(fenye.getCurrentPageNo()-1)*fenye.getPageSize());
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("fenye", fenye);
        return "html/Staffmanagement";
    }
    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "/getEmpBycondition", method = RequestMethod.POST)
    public Object getEmpBycondition(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName,@RequestParam(value = "pageNo", required = false)Integer pageNo
            ,@RequestParam(value = "roleID",required = false)String roleID){
        int Count = empService.getCount(entry_starttime, entry_prefixtime, empName, roleID);
        if (pageNo==null) {
            pageNo=1;
        }
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(Count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<employee> employeeList = (List<employee>)empService
                .getEmpBycondition(entry_starttime, entry_prefixtime, empName, roleID,(fenye.getCurrentPageNo()-1)*fenye.getPageSize());
        Map<String, Object> empMap = new HashMap<>();
        empMap.put("employeeList",employeeList);
        empMap.put("fenye",fenye);
        return empMap;
    }

    @ResponseBody
    @RequestMapping(value = "deleteEmp")
    public Object deleteEmp(@RequestParam("emp_id")Integer emp_id,@RequestParam("empName")String empName){
        employee employee = empService.getEmpByname(empName);
        int result = empService.deleteEmp(emp_id);
        if (result==1) {
            String filePath = "D:\\ideas\\library\\springcloud-library-product\\src\\main\\resources\\static\\images\\tou\\";
            filePath = filePath + employee.getImgpath();
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        return result;
    }


    @RequestMapping(value = "doAddEmp",method = RequestMethod.GET)
    public String doAddEmp(){
        return "html/addEmp";
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Object upload(@RequestParam("image_file")MultipartFile multipartFile, employee employee,@RequestParam("uname")String uname,HttpServletRequest request){
        System.out.println("当前路径:"+request.getSession().getServletContext().getRealPath("/"));
        System.out.println("进入到文件上传..........");
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 重新生成文件名
        fileName = employee.getEmp_username() + suffixName;
        employee.setImgpath(fileName);
        // 指定本地文件夹存储图片
        String filePath = "D:\\ideas\\library\\springcloud-library-product\\src\\main\\resources\\static\\images\\tou\\";
        try {
            // 将图片保存到tou文件夹中
            multipartFile.transferTo(new File(filePath + fileName));
            empService.addEmp(employee);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/library/doindex";
    }

    /**
     * 获取服务部署根路径 http:// + ip + port
     *
     * @param request
     * @return
     */
    private String getServerIPPort(HttpServletRequest request) {
        //+ ":" + request.getServerPort()
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }

    @SuppressWarnings("all")
    @RequestMapping(value = "dobooklist", method = RequestMethod.GET)
    public String dobooklist(Model model){
        int count = bookService.getCount(null, null,null);
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(1);
        fenye.setTotalCount(count);
        List<book> bookList = bookService.getBooklist((fenye.getCurrentPageNo()-1)*fenye.getPageSize(),fenye.getPageSize()
                ,null, null,null);
        model.addAttribute("bookList", bookList);
        model.addAttribute("fenye", fenye);
        return "html/booklist";
    }

    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "booklistby", method = RequestMethod.GET)
    public Object booklistby(@RequestParam(value = "pageNo",required = false)Integer pageNo
            , @RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_damage",required = false)Integer book_damage){
        int count = bookService.getCount(book_name, book_authod,book_damage);
        if (pageNo==null) {
            pageNo=1;
        }
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<book> bookList = bookService.getBooklist((fenye.getCurrentPageNo()-1)*fenye.getPageSize(),fenye.getPageSize()
                ,book_name, book_authod,book_damage);
        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("fenye", fenye);
        bookMap.put("bookList", bookList);
        return bookMap;
    }

    @RequestMapping(value = "addBook",method = RequestMethod.GET)
    public String addBook(){
        return "html/addBook";
    }

    @ResponseBody
    @RequestMapping(value = "doaddBook", method = RequestMethod.POST)
    public Object doaddBook(@RequestParam(value = "book_price",required = false) BigDecimal book_price
            , @RequestParam(value = "book_name",required = false)String book_name
            , @RequestParam(value = "book_authod",required = false)String book_authod
            , @RequestParam(value = "book_type",required = false)Integer book_type){

        return bookService.addBook(book_price,book_name,book_authod,book_type);
    }

    @RequestMapping(value = "book_sell",method = RequestMethod.GET)
    public String book_sell(){
        return "html/book_sell";
    }

    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "select_book", method = RequestMethod.GET)
    public Object select_book(book book){
        int count = bookService.getCount(book.getBook_name(), book.getBook_authod(),book.getBook_damage());
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(1);
        fenye.setTotalCount(count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<book> bookList = bookService.getBooklist((fenye.getCurrentPageNo()-1)*fenye.getPageSize(),fenye.getPageSize()
                ,book.getBook_name(), book.getBook_authod(),book.getBook_damage());
        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("bookList",bookList);
        bookMap.put("fenye",fenye);
        return bookMap;
    }

    @ResponseBody
    @RequestMapping(value = "get_book",method = RequestMethod.GET)
    public Object get_book(@RequestParam(value = "book_id")Integer book_id){
        book book = bookService.get_book(book_id);
        if (book==null) {
            return 0;
        }
        return bookService.get_book(book_id);
    }

    @ResponseBody
    @RequestMapping(value = "sellbook",method = RequestMethod.GET)
    public Object sellbook(@RequestParam(value = "book_name",required = false)String book_name,
                           @RequestParam(value = "book_authod",required = false)String book_authod,
                           @RequestParam(value = "book_price",required = false) BigDecimal book_price,
                           @RequestParam(value = "book_type",required = false)Integer book_type,
                           @RequestParam(value = "book_id",required = false)Integer book_id,
                           @RequestParam(value = "handler",required = false)String handler){
        return bookService.sellbook(book_name, book_authod, book_price, book_type, book_id,handler);
    }

    @RequestMapping(value = "do_loanBook",method = RequestMethod.GET)
    public String do_loanBook(){
        return "html/loan_Book";
    }

    @ResponseBody
    @RequestMapping(value = "get_loanUser",method = RequestMethod.GET)
    public Object get_loanUser(@RequestParam(value = "loan_id",required = false)Integer loan_id){
        user loanUser = empService.get_loanUser(loan_id);
        if (loanUser==null) {
            return 0;
        }
        return loanUser;
    }

    @ResponseBody
    @RequestMapping(value = "loan_Book",method = RequestMethod.POST)
    public Object loan_Book(bookloan bookloan){
        return bookService.loan_Book(bookloan);
    }

    @SuppressWarnings("all")
    @RequestMapping(value = "loanList",method = RequestMethod.GET)
    public String do_loanList(@RequestParam(value = "pageNo",required = false)Integer pageNo,
                              @RequestParam(value = "book_name",required = false)String book_name,
                              @RequestParam(value = "loan_loantimeP",required = false)String loan_loantimeP,
                              @RequestParam(value = "loan_loantimeS",required = false)String loan_loantimeS,Model model){
        int Count = bookService.getloanCount(book_name,loan_loantimeP,loan_loantimeS);
        if (pageNo==null) {
            pageNo=1;
        }
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(Count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<bookloan> bookloanList = bookService.getBookloanList(book_name,loan_loantimeP,loan_loantimeS
            ,(fenye.getCurrentPageNo()-1)*fenye.getPageSize(),fenye.getPageSize());
        model.addAttribute("bookloanList",bookloanList);
        model.addAttribute("fenye",fenye);
        return "html/loan_List";
    }

    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "ajax_loanList",method = RequestMethod.GET)
    public Object ajax_loanList(@RequestParam(value = "pageNo",required = false)Integer pageNo,
                              @RequestParam(value = "book_name",required = false)String book_name,
                              @RequestParam(value = "loan_loantimeP",required = false)String loan_loantimeP,
                              @RequestParam(value = "loan_loantimeS",required = false)String loan_loantimeS){
        int Count = bookService.getloanCount(book_name,loan_loantimeP,loan_loantimeS);
        if (pageNo==null) {
            pageNo=1;
        }
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(Count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<bookloan> bookloanList = bookService.getBookloanList(book_name,loan_loantimeP,loan_loantimeS
                ,(fenye.getCurrentPageNo()-1)*fenye.getPageSize(),fenye.getPageSize());
        Map<String, Object> loanMap = new HashMap<>();
        loanMap.put("bookloanList",bookloanList);
        loanMap.put("fenye",fenye);
        return loanMap;
    }

    @ResponseBody
    @RequestMapping(value = "return_book", method = RequestMethod.GET)
    public Object return_book(@RequestParam(value = "loan_id",required = false)Integer loan_id){
        return bookService.return_book(loan_id);
    }

    @SuppressWarnings("all")
    @RequestMapping(value = "do_jidu",method = RequestMethod.GET)
    public String do_jidu(@RequestParam(value = "pageNo",required = false)Integer pageNo,
                          @RequestParam(value = "pageNos",required = false)Integer pageNos,
                          @RequestParam(value = "book_name",required = false)String book_name,
                          @RequestParam(value = "loan_loantimeP",required = false)String loan_loantimeP,
                          @RequestParam(value = "loan_loantimeS",required = false)String loan_loantimeS,Model model){
        int Count = bookService.getloanCount(book_name,loan_loantimeP,loan_loantimeS);
        int sellCount = bookService.getbooksellCount();
        if (pageNo==null) {
            pageNo=1;
        }
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(Count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<bookloan> bookloanList = bookService.getBookloanList(book_name,loan_loantimeP,loan_loantimeS
                ,(fenye.getCurrentPageNo()-1)*fenye.getPageSize(),fenye.getPageSize());

        // booksell分页
        if (pageNos==null) {
            pageNos=1;
        }
        fenye fenyes = new fenye();
        fenyes.setPageSize(5);
        fenyes.setCurrentPageNo(pageNos);
        fenyes.setTotalCount(sellCount);
        if(fenyes.getCurrentPageNo()>fenyes.getTotalPageCount()){
            fenyes.setCurrentPageNo(fenyes.getTotalPageCount());
        }else if(fenyes.getCurrentPageNo()<1){
            fenyes.setCurrentPageNo(1);
        }
        List<booksell> booksell = bookService.getbooksell((fenyes.getCurrentPageNo() - 1) * fenye.getPageSize(), fenyes.getPageSize());
        model.addAttribute("bookloanList",bookloanList);
        model.addAttribute("booksell",booksell);
        model.addAttribute("fenye",fenye);
        model.addAttribute("fenyes",fenyes);
        return "html/jidu_List";
    }

    @SuppressWarnings("all")
    @RequestMapping(value = "Emp_wage",method = RequestMethod.GET)
    public String Emp_wage(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName
            ,@RequestParam(value = "pageNo", required = false)Integer pageNo
            ,@RequestParam(value = "emp_id", required = false)Integer emp_id, Model model){
        int Count = empService.getEmp_wageCount(entry_starttime,entry_prefixtime,empName,emp_id);
        if (pageNo==null) {
            pageNo=1;
        }
        fenye fenye = new fenye();
        fenye.setPageSize(5);
        fenye.setCurrentPageNo(pageNo);
        fenye.setTotalCount(Count);
        if(fenye.getCurrentPageNo()>fenye.getTotalPageCount()){
            fenye.setCurrentPageNo(fenye.getTotalPageCount());
        }else if(fenye.getCurrentPageNo()<1){
            fenye.setCurrentPageNo(1);
        }
        List<attendance> emp_wageList = empService.getEmp_wage(entry_starttime, entry_prefixtime, empName, emp_id);
        model.addAttribute("emp_wageList",emp_wageList);
        return "";
    }
}
