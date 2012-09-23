/*
 * 由SharpDevelop创建。
 * 用户： wellbeing
 * 日期: 2012/9/23
 * 时间: 8:46
 * 
 * 要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 */
using System;

namespace NewWisoftUpdateTool.common
{
	/// <summary>
	/// Description of MainProTreeClickEventArgs.
	/// </summary>
	public delegate void MainProTreeClickEventHandler(object serder, MainProTreeClickEventArgs e);
	public class MainProTreeClickEventArgs:EventArgs
	{
		public readonly PackProcess NodeType;
		public readonly WiFile EditWiFile;
		public MainProTreeClickEventArgs(PackProcess _NodeType,WiFile _wifile)
		{
			NodeType = _NodeType;
			EditWiFile = _wifile;
		}
	}
}
