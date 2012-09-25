/*
 * 由SharpDevelop创建。
 * 用户： wellbeing
 * 日期: 2012/9/16
 * 时间: 21:22
 * 
 * 要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 */
using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using System.Collections.Generic;
using NewWisoftUpdateTool.common;

namespace NewWisoftUpdateTool.ui
{
	/// <summary>
	/// Description of MainProView.
	/// </summary>
	public partial class MainProView : UserControl
	{
		
		Dictionary<PackProcess,UserControl> d = new Dictionary<PackProcess,UserControl>();
		
		private WiFile current_wifile = null;
		
		public MainProView()
		{
			//
			// The InitializeComponent() call is required for Windows Forms designer support.
			//
			InitializeComponent();
			initSubViews();
			//
			// TODO: Add constructor code after the InitializeComponent() call.
			//
		}
		
		/// <summary>
		/// 初始化试图时添加试图的方法
		/// </summary>
		private void addSubView()
		{
			foreach (var element in d) {
				UserControl uc = element.Value;
				this.panel1.Controls.Add(uc);
				uc.Anchor =((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
									| System.Windows.Forms.AnchorStyles.Left) 
									| System.Windows.Forms.AnchorStyles.Right)));
				uc.Dock = DockStyle.Fill;
				uc.Visible = false;
			}
			
		}
		
		/// <summary>
		/// 初始化试图
		/// </summary>
		private void initSubViews()
		{
			d.Add(PackProcess.Define_Base,new SubView_Define_Base() );
			d.Add(PackProcess.Select_Files,new SubView_Select_Files() );
			d.Add(PackProcess.Edit_Configs,new SubView_Edit_Configs() );
			d.Add(PackProcess.Edit_Sql,new SubView_Edit_Sql() );
			addSubView();
			setViewVisible(PackProcess.Select_Files);
		}
		
		public void WifileDataBinding(WiFile wifile)
		{
			this.current_wifile = wifile;
			
		}
		
		/// <summary>
		/// 控制试图显示的方法
		/// </summary>
		/// <param name="a"></param>
		public void setViewVisible(PackProcess a)
		{
			foreach (var element in d) {
				if(element.Key == a)
					element.Value.Visible =true;
				else
					element.Value.Visible =false;
			} 
		}
		
	}
}
