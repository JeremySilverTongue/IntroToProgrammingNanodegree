def apply_template(template_path, **kw):
    """
    Given the path to a template, render that template, passing along any
    keyword args.
    """
    with open(template_path) as template_file:
        template = template_file.read()
        return template.format(**kw)
