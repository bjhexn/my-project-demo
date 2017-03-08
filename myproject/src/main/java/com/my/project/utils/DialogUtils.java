package com.my.project.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;


public class DialogUtils extends Dialog {
    public DialogUtils(Context context) {
        super(context);
    }

    public DialogUtils(Context context, int theme) {
        super(context, theme);
    }
    /**
	 * 弹出进度提示对话框
	 */
	public static ProgressDialog progressDialog;

	/**
	 * 创建普通对话框
	 * 
	 * @param context
	 *            上下文 必填
	 * @param title
	 *            标题
	 * @param message
	 *            显示内容
	 * @param yesName
	 *            按钮名称
	 * @param listener
	 *            监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
	 * @return
	 */
	public static void showConfirmDialog(Context context, String title,
										 String message, String yesName, String cancelName,
										 final ConfirmListener listener, boolean canOutside) {
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				context);
		// 设置对话框的图标
		// builder.setIcon(iconId);
		// 设置对话框的标题
		if (null != title) {
			builder.setTitle(title);
		}
		// 设置对话框的显示内容
		builder.setMessage(message);
		// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
		if (null != yesName) {
			builder.setPositiveButton(yesName, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (null != listener) {
						listener.onOk();
					}
				}
			});
		}
		if (null != cancelName) {
			builder.setNegativeButton(cancelName,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
							if (null != listener) {
								listener.onCancel();
							}
						}
					});
		}
		// 创建一个普通对话框
		dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(canOutside);
		dialog.show();
	}
	/**
	 * 创建普通对话框
	 *
	 * @param context
	 *            上下文 必填
	 * @param title
	 *            标题
	 * @param message
	 *            显示内容
	 * @param yesName
	 *            按钮名称
	 * @param listener
	 *            监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
	 * @param canOutside
	 * 			  能否点击外部取消对话框
	 * @param view
	 * 			  设置contentView
	 */
	public static void showConfirmDialogCustom(Context context, String title,
											   String message, String yesName, String cancelName,
											   final ConfirmListener listener, boolean canOutside, View view) {
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				context);
		// 设置对话框的图标
		// builder.setIcon(iconId);
		// 设置对话框的标题
		if (null != title) {
			builder.setTitle(title);
		}
		// 设置对话框的显示内容
		if (null!=message) {
			builder.setMessage(message);
		}
		// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
		if (null != yesName) {
			builder.setPositiveButton(yesName, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (null != listener) {
						listener.onOk();
					}
				}
			});
		}
		if (null != cancelName) {
			builder.setNegativeButton(cancelName,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					if (null != listener) {
						listener.onCancel();
					}
				}
			});
		}
		builder.setView(view);
		// 创建一个普通对话框
		dialog = builder.create();
		dialog.setCancelable(canOutside);
		dialog.setCanceledOnTouchOutside(canOutside);
		dialog.show();
	}


	/**
	 * 创建能点击内容的对话框
	 *
	 * @param context
	 *            上下文 必填
	 * @param title
	 *            标题
	 * @param message
	 *            显示内容
	 * @param yesName
	 *            按钮名称
	 * @param listener
	 *            监听器，需实现android.content.DialogInterface.OnClickListener接口 必填
	 * @param canOutside
	 * 			  能否点击外部取消对话框
	 * @param view
	 * 			  设置contentView
	 */
	public static void showConfirmDialogCustomViewClick(final Context context, String title,
														String message, String yesName, String cancelName,
														final ConfirmListener listener, boolean canOutside, View view) {
		Dialog dialog = null;
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				context);
		// 设置对话框的图标
		// builder.setIcon(iconId);
		// 设置对话框的标题
		if (null != title) {
			builder.setTitle(title);
		}
		// 设置对话框的显示内容
		builder.setMessage(message);
		// 添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
		if (null != yesName) {
			builder.setPositiveButton(yesName, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (null != listener) {
						listener.onOk();
					}
				}
			});
		}
		if (null != cancelName) {
			builder.setNegativeButton(cancelName,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					if (null != listener) {
						listener.onCancel();
					}
				}
			});
		}
		
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String url=	(String) arg0.getTag();
			}
		});
		builder.setView(view);
		// 创建一个普通对话框
		dialog = builder.create();
		dialog.setCancelable(canOutside);
		dialog.setCanceledOnTouchOutside(canOutside);
		dialog.show();
	}

	/**
	 * 自定义ProgressDialog
	 * 
	 * @param context
	 * @param message
	 */
	public static void showProgressDialogAuto(Context context, String message) {
		if (null == context) {
			return;
		}
		synchronized (context) {
			if (progressDialog == null) {
				progressDialog = ProgressDialog.show(context, null, message);
			}
		}
	}
	
	/**
	 * 得到一个ProgressDialog
	 * 
	 * @param context
	 * @param message
	 * @param isNeedCancel 按back键是否能回退
	 * @param isNeedOutsideDismiss 点击dialog外是否能回退
	 * @param listner 取消时的监听事件
	 * @return
	 */
	public static void showProgressDialog(Context context,
										  String message, boolean isNeedCancel, boolean isNeedOutsideDismiss, OnCancelListener listner) {
		if (null == context) {
			return;
		}
		synchronized (context) {
//			if (progressDialog == null) {
				progressDialog = new ProgressDialog(context);
				progressDialog.setTitle(null);
				progressDialog.setMessage(message);
				progressDialog.setCancelable(isNeedCancel);
				progressDialog.setCanceledOnTouchOutside(isNeedOutsideDismiss);
				progressDialog.setOnCancelListener(listner);
//			}
			
			progressDialog.show();
//			progressDialog = ProgressDialog.show(context, "提示", message);
		}
		
	
	}

	public synchronized static void closeProgressDialog() {
		if (null != progressDialog && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	
	
	/**
	 * 
	 * @param context
	 * @param message
	 * @param isNeedCancel
	 *            是否能回退
	 */
	public static ProgressDialog getProgressDialog(Context context,
												   String message, boolean isNeedCancel) {
		ProgressDialog progressDialog = null;
		if (null == context) {
			return null;
		}
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("提示");
		progressDialog.setMessage(message);
		progressDialog.setCancelable(isNeedCancel);
		return progressDialog;
	}

	/**
	 * 得到一个ProgressDialog
	 * 
	 * @param context
	 * @param message
	 * @param isNeedCancel 按back键是否能回退
	 * @param isNeedOutsideDismiss 点击dialog外是否能回退
	 * @param listner 取消时的监听事件
	 * @return
	 */
	public static ProgressDialog getProgressDialog(Context context,
												   String message, boolean isNeedCancel, boolean isNeedOutsideDismiss, OnCancelListener listner) {
		ProgressDialog progressDialog = null;
		if (null == context) {
			return null;
		}
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("提示");
		progressDialog.setMessage(message);
		progressDialog.setCancelable(isNeedCancel);
		progressDialog.setCanceledOnTouchOutside(isNeedOutsideDismiss);
		progressDialog.setOnCancelListener(listner);
		return progressDialog;
	}

	public interface ConfirmListener {

		void onOk();

		void onCancel();
	}

}
