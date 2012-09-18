/*
 * 由SharpDevelop创建。
 * 用户： wellbeing
 * 日期: 2012/9/16
 * 时间: 21:22
 * 
 * 要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 */
namespace NewWisoftUpdateTool.ui
{
	partial class MainProView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		
		/// <summary>
		/// Disposes resources used by the control.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing) {
				if (components != null) {
					components.Dispose();
				}
			}
			base.Dispose(disposing);
		}
		
		/// <summary>
		/// This method is required for Windows Forms designer support.
		/// Do not change the method contents inside the source code editor. The Forms designer might
		/// not be able to load this method if it was changed manually.
		/// </summary>
		private void InitializeComponent()
		{
			this.panel1 = new System.Windows.Forms.Panel();
			this.subView_overview1 = new NewWisoftUpdateTool.ui.SubView_overview();
			this.panel1.SuspendLayout();
			this.SuspendLayout();
			// 
			// panel1
			// 
			this.panel1.Controls.Add(this.subView_overview1);
			this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
			this.panel1.Location = new System.Drawing.Point(0, 0);
			this.panel1.Name = "panel1";
			this.panel1.Size = new System.Drawing.Size(607, 474);
			this.panel1.TabIndex = 0;
			// 
			// subView_overview1
			// 
			this.subView_overview1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
									| System.Windows.Forms.AnchorStyles.Left) 
									| System.Windows.Forms.AnchorStyles.Right)));
			this.subView_overview1.Location = new System.Drawing.Point(3, 3);
			this.subView_overview1.Name = "subView_overview1";
			this.subView_overview1.Size = new System.Drawing.Size(601, 468);
			this.subView_overview1.TabIndex = 0;
			// 
			// MainProView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.Controls.Add(this.panel1);
			this.Name = "MainProView";
			this.Size = new System.Drawing.Size(607, 474);
			this.panel1.ResumeLayout(false);
			this.ResumeLayout(false);
		}
		private NewWisoftUpdateTool.ui.SubView_overview subView_overview1;
		private System.Windows.Forms.Panel panel1;
	}
}
