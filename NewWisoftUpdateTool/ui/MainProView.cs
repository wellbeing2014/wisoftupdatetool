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

namespace NewWisoftUpdateTool.ui
{
	/// <summary>
	/// Description of MainProView.
	/// </summary>
	public partial class MainProView : UserControl
	{
		public MainProView()
		{
			//
			// The InitializeComponent() call is required for Windows Forms designer support.
			//
			InitializeComponent();
			
			//
			// TODO: Add constructor code after the InitializeComponent() call.
			//
		}
		
		private void addSubView(UserControl uc)
		{
			this.panel1.Controls.Add(uc);
			uc.Anchor = AnchorStyles.Left|AnchorStyles.Bottom|AnchorStyles.Right|AnchorStyles.Top;
		}
	}
}
