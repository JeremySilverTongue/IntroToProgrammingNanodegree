import json
import constants

def parse_content(content_path = constants.CONTENT_PATH):
    """ Parses a JSON file at a given path. """
    with open(content_path) as content_file:
        return json.loads(content_file.read())
