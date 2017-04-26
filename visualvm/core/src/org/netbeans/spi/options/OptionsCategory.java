package org.netbeans.spi.options;

import java.util.Map;
import javax.swing.Icon;

public abstract class OptionsCategory {

    private static final String TITLE = "title";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String ICON = "iconBase";
    private static final String CONTROLLER = "controller";
    private static final String KEYWORDS = "keywords";
    private static final String KEYWORDS_CATEGORY = "keywordsCategory";
    private static final String ADVANCED_OPTIONS_FOLDER = "advancedOptionsFolder";

    public OptionsCategory() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokespecial java/lang/Object."<init>":()V
         * 4: return
         *  */
        // </editor-fold>
    }

    public String getIconBase() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aconst_null
         * 1: areturn
         *  */
        // </editor-fold>
        return new String();
    }

    public Icon getIcon() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: new           java/lang/StringBuilder
         * 3: dup
         * 4: invokespecial java/lang/StringBuilder."<init>":()V
         * 7: aload_0
         * 8: invokevirtual org/netbeans/spi/options/OptionsCategory.getIconBase:()Ljava/lang/String;
         * 11: invokevirtual java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         * 14: ldc           .png
         * 16: invokevirtual java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         * 19: invokevirtual java/lang/StringBuilder.toString:()Ljava/lang/String;
         * 22: iconst_1
         * 23: invokestatic  org/openide/util/ImageUtilities.loadImageIcon:(Ljava/lang/String;Z)Ljavax/swing/ImageIcon;
         * 26: astore_1
         * 27: aload_1
         * 28: ifnonnull     58
         * 31: new           java/lang/StringBuilder
         * 34: dup
         * 35: invokespecial java/lang/StringBuilder."<init>":()V
         * 38: aload_0
         * 39: invokevirtual org/netbeans/spi/options/OptionsCategory.getIconBase:()Ljava/lang/String;
         * 42: invokevirtual java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         * 45: ldc           .gif
         * 47: invokevirtual java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
         * 50: invokevirtual java/lang/StringBuilder.toString:()Ljava/lang/String;
         * 53: iconst_1
         * 54: invokestatic  org/openide/util/ImageUtilities.loadImageIcon:(Ljava/lang/String;Z)Ljavax/swing/ImageIcon;
         * 57: astore_1
         * 58: aload_1
         * 59: areturn
         *  */
        // </editor-fold>
        return null;
    }

    public abstract String getCategoryName();

    public abstract String getTitle();

    public abstract OptionsPanelController create();

    static OptionsCategory createCategory(Map attrs) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: ldc           title
         * 3: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
         * 8: checkcast     java/lang/String
         * 11: astore_1
         * 12: aload_0
         * 13: ldc           categoryName
         * 15: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
         * 20: checkcast     java/lang/String
         * 23: astore_2
         * 24: aload_0
         * 25: ldc           iconBase
         * 27: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
         * 32: checkcast     java/lang/String
         * 35: astore_3
         * 36: aload_0
         * 37: ldc           keywords
         * 39: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
         * 44: checkcast     java/lang/String
         * 47: astore        4
         * 49: aload_0
         * 50: ldc           keywordsCategory
         * 52: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
         * 57: checkcast     java/lang/String
         * 60: astore        5
         * 62: aload_0
         * 63: ldc           advancedOptionsFolder
         * 65: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
         * 70: checkcast     java/lang/String
         * 73: astore        6
         * 75: new           org/netbeans/modules/options/OptionsCategoryImpl
         * 78: dup
         * 79: aload_1
         * 80: aload_2
         * 81: aload_3
         * 82: new           org/netbeans/spi/options/OptionsCategory$1
         * 85: dup
         * 86: aload_0
         * 87: aload_1
         * 88: invokespecial org/netbeans/spi/options/OptionsCategory$1."<init>":(Ljava/util/Map;Ljava/lang/String;)V
         * 91: aload         4
         * 93: aload         5
         * 95: aload         6
         * 97: invokespecial org/netbeans/modules/options/OptionsCategoryImpl."<init>":(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/concurrent/Callable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
         * 100: areturn
         *  */
        // </editor-fold>
        return null;
    }
}
