package shengzhe.haiyan.smartrs.view;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class AutoSplitTextView extends TextView {
    private boolean mEnabled = true;

    public AutoSplitTextView(Context context) {
        super(context);
    }

    public AutoSplitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSplitTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setAutoSplitEnabled(boolean enabled) {
        mEnabled = enabled;
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY 
            && MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY
            && getWidth() > 0 
            && getHeight() > 0
            && mEnabled) {
            String newText = autoSplitText(this);
            if (!TextUtils.isEmpty(newText)) {
                setText(newText);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    private String autoSplitText(final TextView tv) {
        final String rawText = tv.getText().toString(); //ԭʼ�ı�
        final Paint tvPaint = tv.getPaint(); //paint�������������Ϣ
        final float tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight(); //�ؼ����ÿ��
        
        //��ԭʼ�ı����в��
        String [] rawTextLines = rawText.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //������п���ڿؼ����ÿ��֮�ڣ��Ͳ�������
                sbNewText.append(rawTextLine);
            } else {
                //������п�ȳ����ؼ����ÿ�ȣ����ַ��������ڳ������ÿ�ȵ�ǰһ���ַ����ֶ�����
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
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
        
        //�ѽ�β�����\nȥ��
        if (!rawText.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }
        
        return sbNewText.toString();
    }
}