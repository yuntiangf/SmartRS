package shengzhe.haiyan.smartrs.activity;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.ServerUrl;
import shengzhe.haiyan.smartrs.model.LoveFoodIsExist;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.ToastUtils;
import shengzhe.haiyan.smartrs.model.User;
import shengzhe.haiyan.smartrs.util.PreferenceUtil;
import shengzhe.haiyan.smartrs.view.CircleImageView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuDetailActivity extends BaseActivity implements OnClickListener {

	private int menuId, menuMpoint;
	private String menuName, menuProgress, menuCharacter, menuTip,
			menuSeasoning, menuIngredient, menuHabitus, menuPicurl;

	private ImageView back;
	private TextView title_text;

//	private CircleImageView my_photo;
	private ImageView my_photo;
	private TextView my_name, my_intro/*, my_address*/;

	private RelativeLayout menudetail;
	
	private LinearLayout followButton;
	private TextView followStateTip;

	private ImageView detail_picture;
	private TextView menu_name;

	private TextView menu_character;
	private TextView menu_tip;
	private TextView menu_ingredient;
	private TextView menu_seasoning;
	private TextView menu_habitus;
	private TextView menu_progress;
	private LinearLayout layout_character, layout_tip, layout_ingredient,
			layout_seasoning, layout_habitus, layout_progress;

	BitmapUtils bitmapUtils;

	public boolean isExist ;

	private int uid, status;
	private String name = "", headurl = "", intro = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_detail);

		PreferenceUtil.initialize(MenuDetailActivity.this);
		status = PreferenceUtil.getUserStatus();
		uid = PreferenceUtil.getUid();
		if (status == 1) {
			headurl = PreferenceUtil.getHeaderurl();
			name = PreferenceUtil.getUserName();
			intro = PreferenceUtil.getIntro();
		}
		
		getExtra();
		System.out.println("qqqqqqqqqqqqq"+status+"  ");
		findisExist();
		findView();
		setEvent();
	}

	private void findisExist() {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();
		sb.append(ServerUrl.QUERY_MENU_IS_EXIST);
		sb.append(uid);
		sb.append("&&scenic_id=");
		sb.append(menuId);
		final String url = sb.toString();
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				
				LoveFoodIsExist lf = QLParser.parse(responseInfo.result,
						LoveFoodIsExist.class);
				System.out.println(url);
				System.out.println("dadfa"+lf.result);
				isExist = lf.result;
				System.out.println("isexit iiiiiiiiiiiiiiiii"+isExist);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void getExtra() {
		// TODO Auto-generated method stub
		menuId = getIntent().getExtras().getInt("menuId");
		menuName = getIntent().getExtras().getString("menuName");
		menuProgress = getIntent().getExtras().getString("menuProgress");
		menuCharacter = getIntent().getExtras().getString("menuCharacter");
		menuTip = getIntent().getExtras().getString("menuTip");
		menuSeasoning = getIntent().getExtras().getString("menuSeasoning");
		menuIngredient = getIntent().getExtras().getString("menuIngredient");
		menuHabitus = getIntent().getExtras().getString("menuHabitus");
		menuPicurl = getIntent().getExtras().getString("menuPicurl");
		menuMpoint = getIntent().getExtras().getInt("menuMpoint");
	}

	private void findView() {
		// TODO Auto-generated method stub
		menudetail = (RelativeLayout) findViewById(R.id.menudetaillayout);
		
		back = (ImageView) findViewById(R.id.back);
		title_text = (TextView) findViewById(R.id.title_text);

		my_photo = (ImageView) findViewById(R.id.user_photo);
		my_name = (TextView) findViewById(R.id.artist_name);
		my_intro = (TextView) findViewById(R.id.artist_intro);
//		my_address = (TextView) findViewById(R.id.mua_address);

		followButton = (LinearLayout) findViewById(R.id.followButton);
		followStateTip = (TextView) findViewById(R.id.followStateTip);

		detail_picture = (ImageView) findViewById(R.id.detail_picture);
		menu_name = (TextView) findViewById(R.id.menu_detail_name);

		menu_character = (TextView) findViewById(R.id.menu_character);
		menu_tip = (TextView) findViewById(R.id.menu_tip);
		menu_ingredient = (TextView) findViewById(R.id.menu_ingredient);
		menu_seasoning = (TextView) findViewById(R.id.menu_seasoning);
		menu_habitus = (TextView) findViewById(R.id.menu_habitus);
		menu_progress = (TextView) findViewById(R.id.menu_progress);

		layout_character = (LinearLayout) findViewById(R.id.layout_character);
		layout_tip = (LinearLayout) findViewById(R.id.layout_tip);
		layout_ingredient = (LinearLayout) findViewById(R.id.layout_ingredient);
		layout_seasoning = (LinearLayout) findViewById(R.id.layout_seasoning);
		layout_habitus = (LinearLayout) findViewById(R.id.layout_habitus);
		layout_progress = (LinearLayout) findViewById(R.id.layout_progress);
	}

	private void setEvent() {
		
		// TODO Auto-generated method stub
		title_text.setText(menuName + "详情");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);

		followButton.setOnClickListener(this);
		// 登陆状态待写
		bitmapUtils = new BitmapUtils(getApplicationContext());

		if (status == 1) {
			//头像
//			if(headurl == null || headurl.equals("")){
//				headurl = "imgs/hulu.jpg";
//			}
//			bitmapUtils.display(my_photo, ServerUrl.SERVER_ROOT_PATH + headurl);
			
			//用户名
			my_name.setText(name);
			//个性签名
			if(intro == null || intro.equals("")){
				my_intro.setText("学成文武艺，卖与帝王家！");
			}else{
				my_intro.setText(intro);
			}
			
		}
		System.out.println("sssssssssssssssssss"+status);
		if (isExist && status == 1) {
			followStateTip.setText("取消关注");
		} else {
			followStateTip.setText("关注");
		}
		menu_name.setText(menuName);
		if (menuPicurl != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(ServerUrl.SERVER_ROOT_PATH);
			sb.append("imgs/");
			sb.append(menuPicurl);
			String uri = sb.toString();

			bitmapUtils.display(detail_picture, uri);
		} else {
			detail_picture.setImageResource(R.drawable.donggua);
		}

		if (menuCharacter != null) {
			layout_character.setVisibility(View.VISIBLE);
			menu_character.setText(menuCharacter);
		} else {
			layout_character.setVisibility(View.GONE);
		}

		if (menuTip != null) {
			layout_tip.setVisibility(View.VISIBLE);
			menu_tip.setText(menuTip);
		} else {
			layout_tip.setVisibility(View.GONE);
		}

		if (menuSeasoning != null) {
			layout_seasoning.setVisibility(View.VISIBLE);
			menu_seasoning.setText(menuSeasoning);
		} else {
			layout_seasoning.setVisibility(View.GONE);
		}

		if (menuIngredient != null) {
			layout_ingredient.setVisibility(View.VISIBLE);
			menu_ingredient.setText(menuIngredient);
		} else {
			layout_ingredient.setVisibility(View.GONE);
		}

		if (menuHabitus != null) {
			layout_habitus.setVisibility(View.VISIBLE);
			menu_habitus.setText(menuHabitus);
		} else {
			layout_habitus.setVisibility(View.GONE);
		}

		if (menuProgress != null) {
			layout_progress.setVisibility(View.VISIBLE);
			menu_progress.setText(menuProgress);
//			autoSplit();
//			menu_progress.setText(autoSplitText(menu_progress, " "));
			menu_progress.getViewTreeObserver().addOnGlobalLayoutListener(new OnTvGlobalLayoutListener());
			
		} else {
			layout_progress.setVisibility(View.GONE);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back:
			this.finish();
			break;

		case R.id.followButton:
			if (status == 1) {
				if (isExist) {
					deleteMenu();
					System.out.println("ddddddddddddddddd");
				} else {
					addMenu();
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
				}
			} else {
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}

			break;
		default:
			break;
		}
	}

	private void deleteMenu() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();

		sb.append(ServerUrl.DELETE_MENU_TO_LOVEFOOD);
		sb.append(uid);
		sb.append("&&");
		sb.append("scenic_id=");
		sb.append(menuId);
		String url = sb.toString();
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub

				LoveFoodIsExist lf = QLParser.parse(responseInfo.result,
						LoveFoodIsExist.class);
				boolean flag = lf.result;
				if (flag) {
					followStateTip.setText("关注");
					ToastUtils.showToast("取消成功！", MenuDetailActivity.this);
				}else{
					ToastUtils.showToast("取消失败！", MenuDetailActivity.this);
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void addMenu() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();

		sb.append(ServerUrl.ADD_MENU_TO_LOVEFOOD);
		sb.append(uid);
		sb.append("&&");
		sb.append("scenic_id=");
		sb.append(menuId);
		String url = sb.toString();
		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub

				LoveFoodIsExist lf = QLParser.parse(responseInfo.result,
						LoveFoodIsExist.class);
				boolean flag = lf.result;
				if (flag) {
					followStateTip.setText("取消关注");
					ToastUtils.showToast("添加成功！", MenuDetailActivity.this);
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	
	private class OnTvGlobalLayoutListener implements OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            menu_progress.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            final String newText =autoSplitText(menu_progress, "(1)");
            if (!TextUtils.isEmpty(newText)) {
            	menu_progress.setText(newText);
            }
        }
    }
	
	public String autoSplitText(final TextView tv, final String indent) {
        final String rawText = tv.getText().toString(); //原始文本
        final Paint tvPaint = tv.getPaint(); //paint，包含字体等信息
        final float tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight(); //控件可用宽度
        
        //将缩进处理成空格
        String indentSpace = "";
        float indentWidth = 0;
        if (!TextUtils.isEmpty(indent)) {
            float rawIndentWidth = tvPaint.measureText(indent);
            if (rawIndentWidth < tvWidth) {
                while ((indentWidth = tvPaint.measureText(indentSpace)) < rawIndentWidth) {
                    indentSpace += " ";
                }
            }
        }
        
        //将原始文本按行拆分
        String [] rawTextLines = rawText.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //如果整行宽度在控件可用宽度之内，就不处理了
                sbNewText.append(rawTextLine);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
                    //从手动换行的第二行开始，加上悬挂缩进
                    if (lineWidth < 0.1f && cnt != 0) {
                        sbNewText.append(indentSpace);
                        lineWidth += indentWidth;
                    }
                    lineWidth += tvPaint.measureText(String.valueOf(ch));
                    if (lineWidth <= tvWidth) {
                        sbNewText.append(ch);
                    } else {
                        sbNewText.append("\n");
                        lineWidth = 0;
                        --cnt;
                    }
                }
            }
            sbNewText.append("\n");
        }
        
        //把结尾多余的\n去掉
        if (!rawText.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }
        
        return sbNewText.toString();
    }
	
	
	class ViewThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!Thread.currentThread().isInterrupted()) {
				
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
					Thread.currentThread().interrupt();
				}
				
				menudetail.postInvalidate();
			}
		}
		
	}

}
