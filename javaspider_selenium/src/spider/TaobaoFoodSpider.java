package spider;

import com.dataofbank.ce.Spider;
import com.dataofbank.ce.entity.DateBaseConfig;
import com.dataofbank.ce.utils.CommonFieldUtil;
import com.dataofbank.ce.utils.MongoConnectionUtil;
import com.dataofbank.ce.utils.RegexUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/14.
 *爬取淘宝中的美食
 */
public class TaobaoFoodSpider {
    public static int timeout = 10;
    public static void main(String[] args) {
        MongoClient client = new MongoClient("192.168.0.168:27017");
        MongoCollection coll  = client.getDatabase("TM001").getCollection("Tmall_2017-08");
        WebDriver driver = getWebDriver();
        try {
            //login(driver);
            String url = "https://maotai.tmall.com//category.htm?search=y&scene=taobao_shop";
            //url = "https://quanyou.tmall.com/category.htm?spm=a1z10.1-b-s.w14208996-16804746005.12.19335493gI2Trj&search=y&scene=taobao_shop";
            List<Map<String, Object>> list = initShop();
            for(Map<String, Object> map :list){
                url = map.get("网店URL").toString()+"/category.htm?search=y&scene=taobao_shop";
                //url = "https://mjjl.tmall.com//category.htm?search=y&scene=taobao_shop";
                int pageNo = 1;
                String nextPageClass = "";
                driver.get(url);
                while (true){
                    System.out.println("测试----"+ map.get("网店名称").toString() +" - "+pageNo+"-----------------------");
                    //取得下一页元素，css值
                    if(doesWebElementExist(driver,By.className("pagination"))){
                        nextPageClass = driver.findElement(By.className("pagination")).findElement(By.xpath("//a[contains(text(),'下一页')]")).getAttribute("class");
                        if (pageNo != 1) {
                            driver.findElement(By.className("pagination")).findElement(By.xpath("//a[contains(text(),'下一页')]")).click();
                            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                            Spider.sleep(5000);
                        }
                        //获取该分页下的所有商品
                        List<WebElement> lists = driver.findElements(By.cssSelector("div[class='J_TItems']>div[class^='item']"));
                        List<WebElement> subLists = null;
                        Document doc = null;
                        for (WebElement we : lists) {
                            subLists = we.findElements(By.cssSelector("dl[class^='item']"));
                            for (WebElement subWe : subLists) {
                                String shopImage =  subWe.findElement(By.cssSelector(".photo img")).getAttribute("src");
                                String shopTitle = subWe.findElement(By.cssSelector(".photo img")).getAttribute("alt");
                                String shopUrl = subWe.findElement(By.cssSelector(".photo a")).getAttribute("href");
                                String shopId = RegexUtil.findFirst("id=(.*?)&",shopUrl,1);
                                String shopPrice = subWe.findElement(By.cssSelector(".detail .attribute .c-price")).getText();
                                String shopTotalSales = "0";
                                if(doesWebElementExist(driver,By.cssSelector(".detail .attribute .sale-num"))){
                                    shopTotalSales = subWe.findElement(By.cssSelector(".detail .attribute .sale-num")).getText();
                                }else{
                                    shopTotalSales = "没有";
                                }
                                doc = new Document();
                                doc.put("商品ID", shopId);
                                doc.put("商品URL", shopUrl);
                                doc.put("商品名称", shopTitle);
                                doc.put("商品图片",shopImage);
                                doc.put("总销量", shopTotalSales);
                                doc.put("最低价（元）", shopPrice);
                                doc.put("最高价（元）", shopPrice);
                                doc.put("公司名称", map.get("公司名称").toString());
                                doc.put("网店ID", driver.findElement(By.xpath("//*[@id=\"LineZing\"]")).getAttribute("shopid"));
                                doc.put("网店名称", map.get("网店名称").toString());
                                doc.put("网店URL", map.get("网店URL").toString());
                                doc.put("采集平台ID", "TM001");
                                doc.put("信息来源", "天猫");
                                doc.put("终端名称", "手动化");
                                doc.put("程序名称", "天猫-商品销售信息");
                                CommonFieldUtil.addCommonField(doc);
                                coll.insertOne(doc);
                            }
                        }
                        //最后一页退出循环
                        if(nextPageClass.equals("disable")) {
                            break;
                        }
                        pageNo++;
                    }else{
                        System.out.println(map.get("网店名称").toString()+" - 网站不存在");
                        break;
                    }
                }
                Spider.sleep(3000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            client.close();
            driver.close();
            driver.quit();
        }


    }

    /**
     * 登录天猫
     * @param driver
     *//*
    public static void login(WebDriver driver){
        driver.get("https://login.taobao.com/member/login.jhtml?spm=a21wu.241046-us.754894437.1.259ad03avWyKxy&f=top&redirectURL=https%3A%2F%2Fworld.taobao.com%2F");
        driver.findElement(By.xpath("//a[contains(text(),'密码登录')]")).click();
        WebElement user = driver.findElement(By.xpath("//*[@id=\"TPL_username_1\"]"));
        user.clear();
        user.sendKeys("you user");
        WebElement pasw = driver.findElement(By.xpath("//*[@id=\"TPL_password_1\"]"));
        pasw.clear();
        pasw.sendKeys("you pass");
        driver.findElement(By.xpath("//*[@id=\"J_SubmitStatic\"]")).click();
        Spider.sleep(20000);
    }
*/
    /**
     * 按要求搜索
     * @param driver
     */
    public static void search(WebDriver driver,String company) {
    	driver.get("http://kns.cnki.net/kns/brief/default_result.aspx");
    	driver.findElement(By.id("SCOD")).click();
    	
    	//Select sel = new Select(driver.findElement(By.xpath("//select[@id='txt_1_sel']")));
    	//sel.selectByValue("7");
    	driver.findElement(By.xpath("//option[contains(text(),'申请人')]")).click();
    	WebElement com = driver.findElement(By.id("txt_1_value1"));
    	com.clear();
    	com.sendKeys(company);
    	
    }
    /**
     * 初始化驱动，注册驱动
     * initWebDriver
     * @return
     */
    public static WebDriver getWebDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\PanYuanJin\\Desktop\\lib\\chromedriver2.31.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    /**
     * 判断某元素是否存在
     * @param driver
     * @param selector
     * @return
     */
    public static boolean doesWebElementExist(WebDriver driver, By selector)
    {
        try
        {
            driver.findElement(selector);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }
    public static List initShop(){
        DateBaseConfig query = new DateBaseConfig();
        query.setIp("192.168.0.20").setDataBaseName("TM001").setTableName("Tmall0");
        List<Document> comList = MongoConnectionUtil.getQueryList(query);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (org.bson.Document doc : comList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("公司名称", doc.getString("公司名称"));
            map.put("网店名称", doc.getString("网店名称"));
            map.put("网店URL", doc.getString("网店URL"));
            String shop = "";
            Matcher matcher = Pattern.compile("https://\\S+\\.(tmall|taobao).(com|hk)").matcher(doc.getString("网店URL"));
            if (matcher.find()) {
                shop = matcher.group(0).replace("taobao", "tmall");
            } else {
                continue;
            }
            map.put("商店主网址", shop);
            list.add(map);
        }
        if(list.size() == 0 ){
            System.out.println("从数据库获取店铺信息失败！");
        }
        MongoConnectionUtil.close();
        return list;
    }
}
