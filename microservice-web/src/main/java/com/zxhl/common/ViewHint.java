package com.zxhl.common;

/**
 * Created by mafei on 2016/3/16.
 */
public enum ViewHint {
    Success("操作成功", 200),
    Fail("操作失败", 500),
    ParameterError("参数错误", 400),
    NotFound("资源未找到", 404),
    Exception("操作异常", 550),
    APIException("内部调用异常", 551),
    CheckSuccess("checkyes", 300),
    //手机app验证
    LoginFail("登录验证失败", 501),
    LoginInvalid("登录失效，请重新登录！", 10001),

    //403被占用了,只能用99代替。
    NotAllowed("无权操纵此资源", 99),

    /**
     * 100-200 company
     **/
    //上传营业执照   100~150
    UploadSuccess("营业执照上传成功", 100),
    UploadFail("营业执照上传失败", 101),
    UserException("操作异常(1002)", 102),
    UploadFailForUser("营业执照上传失败", 103),
    CompanyStatus("请完善公司信息", 104),
    UserStatus("用户状态错误", 105),
    CompanyCheckFinish("公司的资质已经通过了审核", 106),
    CompanyLicenseUploaded("公司的资质已经提交，等待审核", 107),
    CompanyCheckPass("个人的公司的资质已经审核通过", 108),
    UserPullBlack("当前账户已被拉黑", 109),
    CompanyFileFail("图片转存失败", 110),
    idCardFileFail("身份证上传失败", 111),
    userAptitudeFail("个人资质上传失败", 112),
    mainUserExist("主账号已经存在,不能完善公司信息", 113),
    UserNotRegister("单位完善信息已经提交", 114),
    orgInfoFinished("单位信息已经填写过", 115),
    passportFileFail("护照上传失败", 116),
    zhimaValidateFail("芝麻认证次数已超限，请选择身份证认证",117),
    //完善公司信息  151~200
    EditSuccess("保存成功", 151),
    EditFail("保存失败", 152),
    NotFount("公司不存在", 153),
    City("请选择城市", 154),
    Companyaddress("公司地址长度不能超过300个字符", 155),
    CompanyName("对外显示名称长度不能超过100个字符", 156),
    Companysize("公司规模类型错误", 157),
    Companytype("公司性质类型错误", 158),
    CompanyCityid("所在城市不能为空", 159),
    Industryid("公司主行业类型数据错误", 160),
    Orgdescription("公司介绍长度不能超过1000个字", 161),
    Contactor("招聘联系人不能为空", 162),
    LicenseUrl("请上传证照原件照片", 163),
    Email("邮箱格式错误", 164),
    LicenseUrlSize("营业执照最多上传5张", 165),//修改5张上传的营业执照
    CompanyNameFail("您输入的单位名称已存在，需要用正确名称重新注册", 166),//公司名称修改失败
    CName("公司名称不能为空且限制4-128个字符",167),
    legalPersoneFail("法人姓名不能为空", 168),
    MinCompanyNameLength("单位名称长度至少4个字符", 169),
    MaxCompanyNameLength("单位名称长度不能超过128个字符", 170),
    StaffName("姓名不能为空且限制1-50个字符",171),


    /**
     * 201-400 job
     **/
    JobNotFount("未找到职位数据", 201),
    CanNotModify("该职位不可修改", 202),
    Positionnature("请选择职位性质", 203),
    Jobtitle("职位名称不能为空且长度不能超过30个汉字、60个英文字符", 204),
    Workplace("上班地址不能为空且长度不能超过50个汉字、100个英文字符", 205),
    OnlineWorkplace("上班地点不能为空且长度不能超过50个汉字、100个英文字符", 205),
    SubJobTypeMain("职位类别错误", 206),
    Monthsalary("请输入合法的职位月薪", 207),
    Mineducationlevel("学历要求不能为空", 208),
    Minyears("工作年限不能为空", 209),
    Quantity("请输入正确的招聘人数", 210),
    Jobdescription("职位描述字数范围为40-6000个字符", 211),
    Benefit("福利标签不能为空且最多可以选择8个", 212),
    Jobfocus("简历关注点不能为空且最多可以选择5个", 213),
    Cityid("请选择城市", 214),
    User("用户数据错误", 215),
    UserNoCheck("审核通过的账号才可以操作", 216),
    Enddate("请选择发布时长", 217),
    JobAddTopTimeCheck("置顶时间不能大于职位发布截止时间", 218),
    JobAddTop("职位置顶时长错误", 219),
    BadWord("职位含有违规词", 220),
    JobRefreshTimeCheck("智能刷新结束时间不能大于职位发布截止时间", 221),
    JobPulishError("职位发布失败", 222),//发布失败
    JobFocusTagError("职位亮点签不能为空且最多可以选择6个", 223),
    JobFocusTagLengthError("长度不能超过10个汉字、20个英文字符", 224),
    WrongJobNumberFormat("职位编号格式错误", 225),
    FailCitys("以下城市有效合同点数不足,发布失败", 226),
    JobtitleHTMLTag("职位名称不能包含富文本标签", 227),
    WorkplaceHTMLTag("地址不能包含富文本标签", 228),
    JobnumberAndCompanyIdMismatching("职位和公司id不匹配", 229),
    //职位模板
    AddJobTemplateSuccess("职位模板添加成功", 251),
    AddJobTemplateFail("职位模板添加失败", 252),
    DeleteJobTemplateSuccess("职位模板删除成功", 253),
    DeleteJobTemplateFail("职位模板删除失败", 254),
    JobTemplateLimitCount("对不起，模板最多设置10个,您当前已设置了%s个模板！", 255),

    //添加、编辑
    JobPriceEndtime("请求数据异常(301)！", 301),//查询需要扣除的智联币数目  职位发布
    JobPriceTop("请求数据异常(302)！", 302),//查询需要扣除的智联币数目  职位置顶
    JobPriceRefresh("请求数据异常(303)！", 303),//查询需要扣除的智联币数目  职位置顶
    JobPriceEx("请求数据异常(304)！", 304),//查询需要扣除的智联币数目  未知异常
    JobPriceSuccess("ok", 305),//查询需要扣除的智联币数目  成功
    ZLBNotEnough("您当前的智联币不足", 306),
    JobSaveFail("暂存失败(307)", 307),//暂存基础数据失败
    JobPulishStateError("该职位已发布,不允许再次发布", 308),//发布中的职位不允许再次发布
    JobTopError("职位发布成功,置顶失败", 309),//职位发布成功,置顶失败
    JobPublishError("发布失败,您的职位已保存到'未发布的职位'", 310),//发布失败,您的职位已保存到'未发布的职位'
    ZLBException("请求数据异常,请刷新重试！", 311),
    ZLBChongzhiTip("您当前的智联币不足,请充值！", 312),
    SmartRefreshNotless30Coins("智能刷新最低消费30智联币", 313),
    JobInReviewing("审核中的职位", 314),
    ContractCountChongzhiTip("您当前的发布合同点数不足,请使用智联币！", 315),
    OnlySave("您的账号尚未通过审核，职位暂不能上线！", 316),
    haveGrayWord("您的职位中存在灰词请修改",317),
    haveGrayWordOnReview("您的职位中存在灰词已进入人工审核",318),
    //职位置顶
    JobTopOutOfStock("当前地区置顶产品已售完，请明天再来",348),
    JobTopMobileStock("抱歉，移动置顶库存不足！", 349),//移动置顶库存不足
    JobTopStock("抱歉，置顶库存不足！", 350),//pc置顶库存不足
    JobTopStatusError("对不起,置顶中职位不允许再置顶", 351),//置顶状态为：发布中 不允许置顶
    JobTopUnPublishing("对不起,发布中的职位才可置顶", 352),//发布中的职位才可置顶
    JobTopStatusNotFound("没有查询到职位置顶状态", 355),//没有查询到职位置顶状态
    JobTopAgain("置顶中职位暂不支持置顶续期哦~", 356),//不能重复置顶
    JobTopFail("置顶失败", 357),//置顶失败
    JobTopTypeError("置顶类型错误", 358),//置顶类型错误
    JobTopZLBPriceNotFound("没有查询到职位置顶价格", 359),//没有查询到职位置顶价格
    JobTopHasEnoughZLCoinsNotFound("查询智联币是否充足失败", 360),//查询智联币是否充足失败

    //职位刷新
    JobRefreshStatusError("只能刷新发布中的职位，请重新选择或者刷新页面重试！", 353),//发布中的职位才能刷新
    JobRefreshError("刷新失败", 354),
    ZLBRefreshNotEnough("刷新所需的智联币不足", 561),
    CountRefreshNotEnough("刷新所需的点数不足", 562),
    ZLBAndCountRefreshNotEnough("刷新所需的点数和智联币不足", 563),
    ExceedCycleRefreshNotEnough("业务异常[超过职位生命周期]", 564),
    ExceedCycleLenghtRefreshNotEnough("刷新时长不可超过职位有效期", 565),
    ZLBNumberRefreshNotEnough("智联币支付失败,请检查余额是否充足!", 566),
    NoCountRefreshNotEnough("扣点失败，请检查点数是否充足", 567),
    NotRepeatJobRefreshError("刷新中职位暂不支持继续刷新", 396),
    //职位关注点 0
    JobFocusSpecialChar("关注点不能含有特殊字符", 361),
    JobFocusLength("关注点不能超过5个", 362),

    //职位状态修改
    JobAuthError("您选择的职位中,存在没有权限操作的职位", 371),
    //职位下线
    jobDownLine("职位下线失败",372),
    JobIsSendInterview("已经发送过面试邀请,不能重复发送", 382),
    /*已下线或已删除的职位不能发送面试邀请*/
    JobDownLineDeletedCheckFail("请先发布要沟通的职位后再操作", 383),
    //到面职位
    PackageCountError("保面试数量3~1000个", 400),
    CanNotPublishJobOnsite("保面试仅在营业执照审核通过之后可使用", 401),
    Reportto("请选择汇报对象", 402),
    Department("所属部门范围为1-50个字符", 403),
    Departmentpeople("请输入正确的部门人数", 405),
    Reporttoyou("请输入正确的下属人数", 406),
    Workdescription("部门描述范围为1-500个字符", 407),
    Advantage("公司优势范围为1-300个字符", 412),
    Interviewaddress("面试地址范围为1-100个字符", 413),
    Interviewcontact("请输入招聘联系人，可由中文或英文组成", 414),
    Interviewcontactnum("请输入手机号", 415),
    JobStyleError("职位类型错误", 426),
    CalendarDateError("日历时间不合法", 426),
    RegulationSubTypeError("日历小类不合法", 427),
    JobStatusError("职位状态错误", 428),
    BasedataError("基础数据错误", 429),
    JobTitledataError("职位名称或职位薪酬错误", 430),
    CalendarEmpty("请编辑面试日历", 431),
    NotOnsiteJob("当前职位不是到面职位", 432),
    PublishEnd("职位不在发布中", 433),
    Arrivaltime("请选择期望到岗时间", 434),
    Sdesiredindustry("从事行业不能大于3个", 435),
    Sage("年龄范围输入不合法", 436),
    Ssalary("月薪范围输入不合法", 437),
    Sgender("性别输入不合法", 438),
    Education("学历输入不合法", 439),
    Smajor("输入专业不合法或专业个数大于3个", 440),
    Language("擅长语言数据不合法", 441),
    ResumeNormalCount("候选人数小于30", 442),
    HasChangeRPO("改职位已转到专属猎头服务", 443),

    /**
     * 601-800 appuser
     **/
    UpdateUserSuccess("更新成功！", 601),
    UpdateUserError("更新失败！", 602),
    PicCodeError("验证码错误", 603),
    MobileCodeError("短信验证码错误", 604),
    MobileError("手机号格式错误", 605),
    Less60S("60秒内已生成验证码", 606),
    More5Times("当天生成验证码超过5次", 607),
    MobileCodeSuccess("验证码生成成功", 608),
    MobileUnChanged("请先修改手机号", 609),
    Expire("验证码已过期", 610),
    NotCheck("用户未审核或审核失败", 611),
    Checking("用户待审核", 612),
    UsersourceError("用户来源错误", 613),
    SendMobileCodeError("短信验证码发送失败", 614),
    NotRdIn("用户不是来自rd导入", 615),
    CAppVersionIsOld("C端用户app版本过低", 616),
    HeaderError("操作异常", 623),
    UrlError("请求地址失效", 624),
    UseManyTimes("验证码已被使用",625),
    UserHasRegistered("该用户已注册，请直接登录", 626),
    OneHourMore3Times("短信验证码1小时内获取超过3次\n请1小时后再尝试获取",627),

    /**
     * 个人资质
     */
    UserCheckPass("个人资质审核完成", 617),
    UserAptitudeUpload("个人已经提交，等待审核", 618),
    UserWaitIhr("个人资质已经提交，等待Ihr审核", 619),
    UserWaitCs("个人资质已经提交，等待客服审核", 620),
    UserTokenLose("用户的token丢效", 621),
    beforeNeedOrgAptitu("请先上传公司资质", 622),
    UserCanNotRecharge("认证通过后才可充值！",628),

    /**
     * 900-1000 到面
     **/
    NeedActiveCalendar("需要激活", 900),


    /**
     * 800-1000 resume
     **/
    JobResumeIdNotFound("未找到投递关系", 800),
    ResumeDeleted("简历已被删除", 801),
    ResumeDownloaded("该候选人已建立联系，请直接查看简历", 802),/*简历已下载*/
    IncludingDownloadedResume("该批简历中包含已下载简历", 803),
    UncheckedUserVisitResumeToLimit("未审核用户无法查看简历", 804),
    VisitResumeToLimit("当日查看简历已达上限", 805),
    ResumeReportToLimit("当日举报简历已达上限", 806),
    ResumeNotFound("未获取到简历", 807),
    SearchResumeToLimit("当日搜索大库简历已达上限", 808),
    DownloadResumeToLimit("审核通过前不允许下载简历", 809),
    CannotInterviewOfCheckFailedJob("未审核通过的职位的简历无法发送面试邀请", 810),
    JobResumeIdSetUnfitFail("投递关系设置不合适失败", 811),
    ContractPointsNotEnough("您当前的点数不足", 812),
    ResumeHaveConnection("该候选人已建立联系，请直接查看简历", 813),
    HomeSearchResumeToLimit("输入次数已达上限", 814),
    ResumeShareTokenError("简历已过期", 815),
    /**
     * 2000-2999 auth
     **/
    LoginInfoNotFound("未获取到用户的登录信息", 2000),
    UserNotFound("未获取到用户", 2001),
    BlackIp("Ip在黑名单中", 2002),
    BlackEmailDomain("邮箱域名在黑名单中", 2003),
    LoginFrequently("登录过于频繁", 2004),
    WrongLoginNameOrPwd("用户名或密码错误", 2005),
    AccountFrozen("账户被冻结", 2006),
    AccountCancelled("账户被取消", 2007),
    AccountLocked("账户被锁定", 2008),
    IpLocked("Ip被锁定", 2009),
    TokenNotFound("未获取到token", 2010),
    CookieParseError("解析ihrcookie失败", 2011),
    LoginedOnOtherDevice("已在别的设备上进行了登录", 2012),
    TooManyWrongPwdTimes("登录密码错误次数过多", 2013),
    //UserAuthFail("用户授权登录失败", 2014),
    ForceLoginout("您的账户在其它设备登录，如非本人操作，请重新登录，并尽快修改密码",2014),
    CompanyNotFound("未获取到公司信息", 2015),
    CheckPassError("认证审核通过后才可登录使用", 2016),
    OhterAccountException("其他登录验证的异常", 2999),

    /**
     * 3000-3499 activity
     **/
    CheckActivityAuth("审核通过后才可以参加活动", 3000),
    RepeartEntry("您已报名成功", 3001),
    RealName("请输入姓名，可由中文或英文组成", 3002),
    Sex("请选择性别", 3003),
    EntityFail("报名失败", 3004),

    /**
     * 4000-4999 settings
     **/
    DataNotModified("数据未发生变更", 4000),
    LocateFail("定位失败", 4001),
    LimitOut("您访问过于频繁，已被限制！", 4015),
    ReLogin("您的账号操作异常，请退出后重新登录！", 4013),
    NOT_ACCESS("您还未获得操作权限！", 4014),

    InvoiceCategory("发票类别错误", 4002),
    InvoiceType("发票抬头错误", 4003),
    InvoiceCompanyName("单位名称请输入1-100个字符", 4004),
    InvoiceContent("发票内容错误", 4005),
    InvoiceReceiver("收件人请输入1-50个字符", 4006),
    InvoiceCompanyAddr("邮寄地址请输入1-100个字符", 4007),
    InvoicePinCode("纳税人识别码请输入15-20个字符", 4008),
    InvoiceRegAddress("注册地址请输入1-100个字符", 4009),
    InvoiceRegTel("注册电话格式错误", 4010),
    InvoiceBankInfo("开户银行请输入1-100个字符", 4011),
    InvoiceAccount("银行账号请输入15-20位数字", 4012),
    ApplePayException("操作异常", 4017),
    ApplePayParaError("苹果凭证不能为空", 4018),
    RechargePriceError("充值失败", 4019),


    /**
     * 5000-5499 settings
     **/
    NonePrize("所有奖品已抽完", 5000),
    NoneDrawTimes("抽奖机会已用完", 5001),
    DrawTimesToLimit("抽奖次数已达上限", 5002),
    LotteryEnd("抽奖已结束", 5003),
    RegionNotAllowed("您所在地区不可参与抽奖", 5004),

    /**
     * 5500-5599 约聊
     **/
    YLStateError("约聊状态错误", 5500),
    YLFreeFinshed("约聊消息已发出，请勿重复发送", 5501),
    JobNotFound("职位不存在", 5502),
    YLHadPaidError("约聊已经支付过", 5503),
    HRBalanceNotEnoughToChat("该HR当前账户余额不足，暂时无法约聊", 5504),//该HR约聊已经欠费达上限，暂时无法约聊
    JobWarDown("职位7天后将要下线", 5505),
    YlNotFound("约聊数据不存在", 5506),
    IsOver15Days("约聊已超过了15天", 5507),
    ResumeFailure("链接失效", 5508),
    JobOffline("该职位已下线，想和求职者在线沟通，快去重新发布职位吧",5509),
    YLCount("您今天约聊发送数已达上限",5510),
    LoginOut("登录账号个数已超限！", 5511),
    YLNotFoundSesstion("约聊没有会话不能发送面试邀请", 5512),
    SessionAddFail("创建会话失败", 5513),
    BeyondCreateShareInfoLimitCount("您的分享操作过于频繁，已被限制访问！",5514),

    /**
     * 5600-5699 会话
     **/
    SessionStateError("会话不存在或者会话状态错误", 5600),
    SessionNotFound("会话不存在", 5601),
    SessionExists("会话已存在", 5602),
    SessionException("获取会话异常", 5603),

    /**
     * 5700-5799  投递
     */
    JobResumeNotToId("职位扩展编号和简历扩展编号与投递ID不匹配", 5700),

    /**
     * 6000-6200 登陆过滤器中使用的code值
     */
    FITLER_TOKEN_NOTFOUND("未获取到token",6000),
    FITLER_USERAUTH_FAIL("验证授权token登录失败",6001),
    FITLER_USER_NOTFOUND("未获取到用户",6002),
    FITLER_ORG_NOTFOUND("未获取到公司",6003),

    FITLER_ORG_NOTUPLOAD_USER_NOTUPLOAD("公司资质未上传，个人资质未上传",6050),
    FITLER_ORG_WAITCHECK_USER_NOTUPLOAD("公司资质待审核，个人资质未上传",6051),
    FITLER_ORG_CHECKFAIL_USER_NOTUPLOAD("公司资质审核不通过，个人资质未上传",6052),
    FITLER_ORG_CHECKSUCCESS_USER_NOTUPLOAD("公司资质审核通过，个人资质未上传",6053),

    FITLER_ORG_WAITCHECK_USER_WAITCHECK("公司资质待审核，个人资质待审核",6054),
    FITLER_ORG_CHECKFAIL_USER_WAITCHECK("公司资质审核不通过，个人资质待审核",6055),
    FITLER_ORG_CHECKSUCCESS_USER_WAITCHECK("公司资质审核通过，个人资质待审核",6056),

    FITLER_ORG_CHECKSUCCESS_USER_CHECKFAIL("公司资质审核通过，个人资质审核不通过",6057),

    FITLER_CHECKFAIL("审核状态不通过",6058);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ViewHint(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ViewHint c : ViewHint.values()) {
            if (c.getKey() == index) {
                return c.name;
            }
        }
        return "";
    }

    // get  方法
    public String getValue() {
        return name;
    }

    public int getKey() {
        return index;
    }
}
