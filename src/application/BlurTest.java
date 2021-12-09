package application;



import java.util.List;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HRESULT;
import com.sun.jna.NativeLibrary;
	

	public class BlurTest {
		public interface Accent {
			public static final int ACCENT_DISABLED = 0;
			public static final int ACCENT_ENABLE_GRADIENT = 1;
			public static final int ACCENT_ENABLE_TRANSPARENTGRADIENT = 2;
			public static final int ACCENT_ENABLE_BLURBEHIND = 3;
			public static final int ACCENT_ENABLE_ACRYLIC = 4; 
			public static final int ACCENT_INVALID_STATE = 5;
		}
	
		
	
		public static class AccentPolicy extends Structure implements Structure.ByReference {
			public  static final List<String> FIELDS = createFieldsOrder("AccentState", "AccentFlags", "GradientColor",
					"AnimationId");
			public int AccentState;
			public int AccentFlags;
			public int GradientColor;
			public int AnimationId;
	
			@Override
			protected List<String> getFieldOrder() {
				return FIELDS;
			}
		}
	
		public static class WindowCompositionAttributeData extends Structure implements Structure.ByReference {
			public static final List<String> FIELDS = createFieldsOrder("Attribute", "Data", "SizeOfData");
			public int Attribute;
			public Pointer Data;
			public int SizeOfData;
	
			@Override
			protected List<String> getFieldOrder() {
				return FIELDS;
			}
		}
	

		public void BlurThis(HWND aeroFrameHWND) {
			NativeLibrary user32 = NativeLibrary.getInstance("user32");
	
			AccentPolicy accent = new AccentPolicy();
			accent.AccentState = Accent.ACCENT_ENABLE_ACRYLIC;
			//32 = noise opacity
			//BGR 12, 12, 12
			accent.GradientColor = 0x32121212;
			accent.write();
	
			WindowCompositionAttributeData data = new WindowCompositionAttributeData();
			data.Attribute = 19;
			data.SizeOfData = accent.size();
			data.Data = accent.getPointer();
	
			Function setWindowCompositionAttribute = user32.getFunction("SetWindowCompositionAttribute");
			setWindowCompositionAttribute.invoke(HRESULT.class, new Object[] { aeroFrameHWND, data });
			
		}
}
