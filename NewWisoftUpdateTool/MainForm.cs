/*
 * 由SharpDevelop创建。
 * 用户： wellbeing
 * 日期: 2012/9/11
 * 时间: 20:50
 * 
 * 要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 */
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using NewWisoftUpdateTool.common;
using NewWisoftUpdateTool.ui;

namespace NewWisoftUpdateTool
{
	/// <summary>
	/// Description of MainForm.
	/// </summary>
	public partial class MainForm : Form
	{
		public WiFile current_wifile = null;
		public MainForm()
		{
			//
			// The InitializeComponent() call is required for Windows Forms designer support.
			//
			InitializeComponent();
			
			//
			// TODO: Add constructor code after the InitializeComponent() call.
			//
			this.mainProTree1.MainProTreeClick+= new MainProTreeClickEventHandler(MainForm_MainProTreeClick);
		}
		
		private void MainForm_MainProTreeClick(object sender, MainProTreeClickEventArgs e)
		{
			current_wifile = e.EditWiFile;
			this.mainProView1.WifileDataBinding(e.EditWiFile);
			this.mainProView1.setViewVisible(e.NodeType);
		}
	}
}
