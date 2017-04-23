package next.web;

import java.util.Map;

public class ModelAndView {
    private View view;
    private Map<String, Object> model;

    public ModelAndView(View view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }

    public ModelAndView(Map<String, Object> model) {
        this.model = model;
    }

    public ModelAndView(View view) {
        this.view = view;
    }

    public ModelAndView() {
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public ModelAndView addObject(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public View getView() {
        return view;
    }

    public ModelAndView setView(View view) {
        this.view = view;
        return this;
    }
}
