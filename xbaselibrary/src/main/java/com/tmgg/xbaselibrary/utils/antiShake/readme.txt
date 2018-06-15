使用：1.在工具类里初始化public static AntiShake antiShakeUtil;

    static {
        antiShakeUtil = new AntiShake();
    }
	2.在需要防止双击的地方
  // TODO: 2017/11/15  防止抖动
                    if(Constants.antiShakeUtil.check()){
                        return;
                    }

2.1在文章开头的那种使用方法仅适用于每个点击事件对应一个方法的情况，那如果有多个id都被绑在同一个方法上，那么使用同一个方法名作为标识就不太合理了。这种情况的使用方法，应如下：

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3})
    public void click(View view) {
        if(Constants.antiShakeUtil.check(view.getId())) return;
    }
       