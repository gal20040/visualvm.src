package org.netbeans.spi.options;

import java.beans.PropertyChangeListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import javax.swing.JComponent;
import org.openide.util.Lookup;

public abstract class OptionsPanelController {

    @Target(value = {ElementType.PACKAGE})
    @Retention(value = RetentionPolicy.SOURCE)
    public @interface ContainerRegistration {

        public String id();

        public String categoryName();

        public String iconBase();

        public String keywords() default "";

        public String keywordsCategory() default "";

        public int position() default 2147483647;
    }

    @Target(value = {ElementType.TYPE})
    @Retention(value = RetentionPolicy.SOURCE)
    public @interface Keywords {

        public String[] keywords();

        public String location();

        public String tabTitle() default "";
    }

    @Target(value = {ElementType.TYPE})
    @Retention(value = RetentionPolicy.SOURCE)
    public @interface KeywordsRegistration {

        public Keywords[] value();
    }

    @Target(value = {ElementType.TYPE, ElementType.METHOD})
    @Retention(value = RetentionPolicy.SOURCE)
    public @interface SubRegistration {

        public String id() default "";

        public String location() default "Advanced";

        public String displayName();

        public String keywords() default "";

        public String keywordsCategory() default "";

        public int position() default 2147483647;
    }

    @Target(value = {ElementType.TYPE, ElementType.METHOD})
    @Retention(value = RetentionPolicy.SOURCE)
    public @interface TopLevelRegistration {

        public String id() default "";

        public String categoryName();

        public String iconBase();

        public String keywords() default "";

        public String keywordsCategory() default "";

        public int position() default 2147483647;
    }
    public static final String PROP_VALID = "valid";
    public static final String PROP_CHANGED = "changed";
    public static final String PROP_HELP_CTX = "helpCtx";

    public OptionsPanelController() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokespecial java/lang/Object."<init>":()V
         * 4: return
         *  */
        // </editor-fold>
    }

    @Deprecated
    public static final OptionsPanelController createAdvanced(String subpath) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: new           org/netbeans/modules/options/advanced/AdvancedPanelController
         * 3: dup
         * 4: aload_0
         * 5: invokespecial org/netbeans/modules/options/advanced/AdvancedPanelController."<init>":(Ljava/lang/String;)V
         * 8: areturn
         *  */
        // </editor-fold>
        return null;
    }

    public abstract void update();

    public abstract void applyChanges();

    public abstract void cancel();

    public abstract boolean isValid();

    public abstract boolean isChanged();

    public Lookup getLookup() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: getstatic     org/openide/util/Lookup.EMPTY:Lorg/openide/util/Lookup;
         * 3: areturn
         *  */
        // </editor-fold>
        return null;
    }

    public void handleSuccessfulSearch(String searchText, List<String> matchedKeywords) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: return
         *  */
        // </editor-fold>
    }

    public abstract JComponent getComponent(Lookup lkp);

    protected void setCurrentSubcategory(String string) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: return
         *  */
        // </editor-fold>
    }

    public final void setSubcategory(String string) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_1
         * 2: invokevirtual org/netbeans/spi/options/OptionsPanelController.setCurrentSubcategory:(Ljava/lang/String;)V
         * 5: return
         *  */
        // </editor-fold>
    }

    public abstract org.openide.util.HelpCtx getHelpCtx();

    public abstract void addPropertyChangeListener(PropertyChangeListener pl);

    public abstract void removePropertyChangeListener(PropertyChangeListener pl);
}
