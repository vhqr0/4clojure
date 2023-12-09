(with-eval-after-load 'cider
  (define-key cider-mode-map (kbd "<f5>") #'cider-eval-buffer))
