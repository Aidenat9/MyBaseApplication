ʹ�ã�1.�ڹ��������ʼ��public static AntiShake antiShakeUtil;

    static {
        antiShakeUtil = new AntiShake();
    }
	2.����Ҫ��ֹ˫���ĵط�
  // TODO: 2017/11/15  ��ֹ����
                    if(Constants.antiShakeUtil.check()){
                        return;
                    }

2.1�����¿�ͷ������ʹ�÷�����������ÿ������¼���Ӧһ�������������������ж��id��������ͬһ�������ϣ���ôʹ��ͬһ����������Ϊ��ʶ�Ͳ�̫�����ˡ����������ʹ�÷�����Ӧ���£�

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3})
    public void click(View view) {
        if(Constants.antiShakeUtil.check(view.getId())) return;
    }
       