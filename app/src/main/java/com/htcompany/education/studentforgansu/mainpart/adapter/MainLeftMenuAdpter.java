package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.activity.AlreadyApplyJobsActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.AssetRepairActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.BodyCheckedActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.ClassAnnouncementActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.ClassTimetableActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.CommpanyInformationActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.ConductEvaluationActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.EmploymentRecordActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.GraduatedCommentsActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.HolidayImportantActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.InternshipRecordActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.MyClassMssageActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.MyExamResultsActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.MyLeaveApplyActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.MyLeaveApplyAddActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.OverallQualityEvaluationActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.PersonInfomationActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.PrizeInfoActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.ScholarshipActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.SchollVotingActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.SchoolBookReserveActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.SchoolBooksActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.SkillsAuthenticateActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.SkillsGameActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.StudentAnnouncementActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.StudentFinancialActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.StudentLoansActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.TeachingInteractionActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.TemporaryHardshipGrantsActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.TermsRemarkActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.TuitionWaiverActivity;
import com.htcompany.education.studentforgansu.mainpart.entity.MainLeftMenuChildEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.MainLeftMenuParentEntity;

import java.util.List;

/**
 * Created by wrb on 2016/10/19.
 * 注册左侧抽屉菜单适配器
 */
public class MainLeftMenuAdpter extends BaseExpandableListAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MainLeftMenuParentEntity> parentDatas;
    private List<List<MainLeftMenuChildEntity>> childDatas;
    public MainLeftMenuAdpter(Context context,List<MainLeftMenuParentEntity> parentDatas,List<List<MainLeftMenuChildEntity>> childDatas){
        this.context= context;
        this.parentDatas = parentDatas;
        this.childDatas = childDatas;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return parentDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childDatas.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childDatas.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View contentView, ViewGroup parent) {
        ParentViewHolder parentViewHolder;
        if(contentView==null){
            parentViewHolder = new ParentViewHolder();
            contentView = inflater.inflate(R.layout.residmenu_left_parent_item,null);
            parentViewHolder.parentitem_name_tv = (TextView)contentView.findViewById(R.id.parentitem_name_tv);
            parentViewHolder.leftmenu_parent_img=(ImageView)contentView.findViewById(R.id.leftmenu_parent_img);
            contentView.setTag(parentViewHolder);
        }else{
            parentViewHolder = (ParentViewHolder) contentView.getTag();
        }
        parentViewHolder.parentitem_name_tv.setText(parentDatas.get(groupPosition).getPname());
        parentViewHolder.leftmenu_parent_img.setImageDrawable(context.getResources().getDrawable(parentDatas.get(groupPosition).getPdid()));
        return contentView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View contentView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(contentView==null){
            childViewHolder = new ChildViewHolder();
            contentView = inflater.inflate(R.layout.residmenu_left_child_item,null);
            childViewHolder.childitem_name_tv = (TextView)contentView.findViewById(R.id.childitem_name_tv);
            childViewHolder.main_leftchild_tv=(TextView)contentView.findViewById(R.id.main_leftchild_tv);
            contentView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder) contentView.getTag();
        }
        childViewHolder.main_leftchild_tv.setBackgroundDrawable(context.getResources().getDrawable(childDatas.get(groupPosition).get(childPosition).getCdid()));
        childViewHolder.childitem_name_tv.setText(childDatas.get(groupPosition).get(childPosition).getCname());
        childViewHolder.childitem_name_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转界面
                if(!"".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())&&childDatas.get(groupPosition).get(childPosition).getViewflag()!=null){
                    Intent intent=null;
                    if ("JBXX".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, PersonInfomationActivity.class);//个人主页
                    }else if("WDCJ".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, MyExamResultsActivity.class);//我的成绩
                    }else if("TJTZ".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, BodyCheckedActivity.class);//体检信息
                    }else if("WDXXK".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
//                        intent = new Intent(context, MyElectiveActivity.class);//我的选修课
                        intent = new Intent(context, ClassTimetableActivity.class);//班级课表
                    }else if("BJMSG".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, MyClassMssageActivity.class);//班级信息
                    }else if("BJGG".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, ClassAnnouncementActivity.class);//班级公告
                    }else if("XSGG".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, StudentAnnouncementActivity.class);//学生公告
                    }else if("ZHSZPJ".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, OverallQualityEvaluationActivity.class);//综合素质评价
                    }else if("XQPY".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, TermsRemarkActivity.class);//学期评语
                    }else if("CXZWPD".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, ConductEvaluationActivity.class);//操行自我评定
                    }else if("JCJL".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, PrizeInfoActivity.class);//奖惩记录
                    }else if("BYPY".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, GraduatedCommentsActivity.class);//毕业评语
                    }else if("XYTP".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, SchollVotingActivity.class);//校园投票
                    }else if("ZCBX".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, AssetRepairActivity.class);//资产报修
                    }else if("JQYSJL".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, HolidayImportantActivity.class);//假期要事记录
                    }else if("WDBJKB".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, ClassTimetableActivity.class);//班级课表
                    }else if("QJSQ".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, MyLeaveApplyAddActivity.class);//请假申请
                    }else if("QJJL".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, MyLeaveApplyActivity.class);//请假记录
                    }else if("JNDS".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, SkillsGameActivity.class);//技能大赛
                    }else if("XYTS".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, SchoolBooksActivity.class);//校园图书
                    }else  if("WDTSYD".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, SchoolBookReserveActivity.class);//校园图书预定
                    }
//                    else if("ZWJY".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
//                        intent = new Intent(context, MyselfEducationActivity.class);//自我教育
//                    }
                    else if("JXJ".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, ScholarshipActivity.class);//奖学金
                    }else if("WDZXJ".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, StudentFinancialActivity.class);//助学金
                    }else if("WDMXF".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, TuitionWaiverActivity.class);//免学费
                    }else if("ZXDK".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, StudentLoansActivity.class);//助学贷款
                    }else if("LSKNBZSQ".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, TemporaryHardshipGrantsActivity.class);//临时困难补助
                    }else if("QYXX".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, CommpanyInformationActivity.class);//企业信息
                    }else if("SXJL".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, InternshipRecordActivity.class);//实习记录
                    }else if("JYJL".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, EmploymentRecordActivity.class);//就业记录
                    }else if("YBMZPGW".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, AlreadyApplyJobsActivity.class);//已报名企业招聘岗位
                    }else if("JXHD".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, TeachingInteractionActivity.class);//教学互动
                    }else if("JNJDGL".equals(childDatas.get(groupPosition).get(childPosition).getViewflag())){
                        intent = new Intent(context, SkillsAuthenticateActivity.class);//技能鉴定
                    }

                    context.startActivity(intent);
                }
            }
        });
        return contentView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public class ParentViewHolder{
        public TextView parentitem_name_tv;
        public ImageView leftmenu_parent_img;
    }
    public class ChildViewHolder{
        public TextView childitem_name_tv,main_leftchild_tv;
    }
}
